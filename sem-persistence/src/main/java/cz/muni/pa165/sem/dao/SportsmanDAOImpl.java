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
	public List<Sportsman> findBySubstring(String substring) {
		if (substring == null) {
			throw new IllegalArgumentException("substring is null");
		}
		if (substring.isEmpty()) {
			return this.findAll();
		}
		try {// TODO: 15-Dec-16 not already enrolled
			return entityManager.createQuery("select s from Sportsman s where LOWER(s.name) LIKE CONCAT('%',:substring,'%')",// OR s.surname LIKE :substring OR s.email LIKE :substring",
					Sportsman.class).setParameter("substring", substring.toLowerCase()).getResultList();
		} catch (NoResultException nrf) {
			return null;
		}
	}
}
