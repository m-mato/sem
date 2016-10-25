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

	public void create(Sportsman sportsman) {

		entityManager.persist(sportsman);
	}

	@Override
	public Sportsman findById(Long id) {
		return entityManager.find(Sportsman.class, id);
	}

	@Override
	public Sportsman findByName(String name) {
		try {
			return entityManager.createQuery("select s from Sportsman s where name = :name",
					Sportsman.class).setParameter("name", name).getSingleResult();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public Sportsman findBySurname(String surname) {
		try {
			return entityManager.createQuery("select s from Sportsman s where surname = :surname",
					Sportsman.class).setParameter("surname", surname).getSingleResult();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public void update(Sportsman sportsman) {
		entityManager.merge(sportsman);
	}

	@Override
	public void delete(Sportsman sportsman) {
		entityManager.remove(sportsman);
	}

	public List<Sportsman> getAll() {

		Query query = entityManager.createQuery("SELECT s FROM Sportsman s");
		return  query.getResultList();
	}
}
