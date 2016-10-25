package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Matej Majdis
 */
@Repository
public class SportsmanDAOImpl implements SportsmanDAO {

	@Autowired
	private EntityManager entityManager;

	public void addSportsman(Sportsman sportsman) {

		entityManager.persist(sportsman);
	}

	public List<Sportsman> getAll() {

		Query query = entityManager.createQuery("SELECT s FROM Sportsman s");
		return  query.getResultList();
	}
}
