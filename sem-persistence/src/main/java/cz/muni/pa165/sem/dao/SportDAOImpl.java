package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kamil Triscik
 */
@Repository
public class SportDAOImpl implements SportDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Sport sport) {
        if (sport == null) {
            throw new IllegalArgumentException("Sport is null");
        }
        em.persist(sport);
    }

    @Override
    public List<Sport> findAll() {
        try {
            Query query = em.createQuery("SELECT s FROM Sport s");
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Sport findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Sport ID is null");
        }
        return em.find(Sport.class, id);
    }

    @Override
    public Sport findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Sport name to find results for is null!");
        }
        try {
            return em.createQuery("select s from Sport s where name = :name",
                    Sport.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public void update(Sport sport) {
        if (sport == null) {
            throw new IllegalArgumentException("Sport is null");
        }
        em.merge(sport);
    }

    @Override
    public void delete(Sport sport) {
        if (sport == null) {
            throw new IllegalArgumentException("Sport is null");
        }
        em.remove(sport);
    }
}
