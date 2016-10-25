package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author Kamil Triscik
 */
@Repository
@Transactional
public class SportDAOImpl implements SportDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Sport sport) {
        em.persist(sport);
    }

    @Override
    public Sport findById(Long id) {
        return em.find(Sport.class, id);
    }

    @Override
    public Sport findByName(String name) {
        try {
            return em.createQuery("select s from Sport s where name = :name",
                    Sport.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public void update(Sport sport) {
        em.merge(sport);
    }

    @Override
    public void delete(Sport sport) {
        em.remove(sport);
    }
}
