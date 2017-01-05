package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Matej Majdis
 */
@Repository
public class SportsmanDAOImpl implements SportsmanDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Sportsman sportsman) {

		if (sportsman == null) {
			throw new IllegalArgumentException("Sportsman is null");
		}
		entityManager.persist(sportsman);
	}

	@Override
	public Sportsman findById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("id is null");
		}
		return entityManager.find(Sportsman.class, id);
	}

	@Override
	public List<Sportsman> findByName(String name) {

		if (name == null) {
			throw new IllegalArgumentException("name is null");
		}
		try {
			List<Sportsman> result = entityManager.createQuery("select s from Sportsman s where name = :name",
					Sportsman.class).setParameter("name", name).getResultList();
			if(result.isEmpty()) {
				return null;
			}
			return result;
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public List<Sportsman> findBySurname(String surname) {

		if (surname == null) {
			throw new IllegalArgumentException("surname is null");
		}
		try {
			List<Sportsman> result = entityManager.createQuery("select s from Sportsman s where surname = :surname",
					Sportsman.class).setParameter("surname", surname).getResultList();
			if(result.isEmpty()) {
				return null;
			}
			return result;
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	//TODO : optimize
	public Sportsman findByEmail(String email) {

		if (email == null) {
			throw new IllegalArgumentException("email is null");
		}
		try {
			List<Sportsman> result = entityManager.createQuery("select s from Sportsman s where email = :email",
					Sportsman.class).setParameter("email", email).getResultList();
			if(result.isEmpty()) {
				return null;
			}
			if(result.size() != 1) {
				throw new IllegalStateException("email is not unique");
			}
			return result.get(0);
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public void update(Sportsman sportsman) {

		if (sportsman == null) {
			throw new IllegalArgumentException("Sportsman is null");
		}
		entityManager.merge(sportsman);
	}

	@Override
	public void delete(Sportsman sportsman) {

		if (sportsman == null) {
			throw new IllegalArgumentException("Sportsman is null");
		}
		entityManager.remove(sportsman);
	}

	@Override
	public List<Sportsman> findAll() {

		Query query = entityManager.createQuery("SELECT s FROM Sportsman s");
		return query.getResultList();
	}

	@Override
	public List<Sportsman> findBySubstring(String substring, Long event_id) {
		if (substring == null) {
			throw new IllegalArgumentException("substring is null");
		}
		if (substring.isEmpty()) {
			return this.findAll();
		}
		Long event = event_id;
		if (event_id == null) {
			event = -1L;
		}
		try {
			Query quer = entityManager.createQuery("select s from Sportsman s where " +
					"(LOWER(s.name) LIKE :substring OR LOWER(s.surname) LIKE :substring OR LOWER(s.email) LIKE :substring) " +
					"AND s.id NOT IN (select r.sportsman.id from Result r where r.event.id=:event_id) " + //not already enrolled
					"AND s.id NOT IN (select i.invitee.id from Invitation i where i.event.id=:event_id)"); //not already invited
			quer.setParameter("substring", "%" + substring + "%");
			quer.setParameter("event_id", event);
			return quer.getResultList();
		} catch (NoResultException nrf) {
			return null;
		}
	}
}
