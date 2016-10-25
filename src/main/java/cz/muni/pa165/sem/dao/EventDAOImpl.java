package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Vit Hovezak
 */
@Repository
public class EventDAOImpl implements EventDAO {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Event event) {
        em.persist(event);
    }

    @Override
    public Event findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Event ID is null");
        }
        return em.find(Event.class, id);
    }

    @Override
    public Event findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Event name is null");
        }
        try {
            Query query = em.createQuery("SELECT e FROM Event e WHERE name = :name");
            query.setParameter("name", name);
            return (Event) query.getSingleResult();
        }
        catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Event> findByDate(Calendar date) {
        if (date == null) {
            throw new IllegalArgumentException("Event date is null");
        }
        try {
            Query query = em.createQuery("SELECT e FROM Event e WHERE date = :date");
            query.setParameter("date", date);
            return query.getResultList();
        }
        catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Event> findBySport(Sport sport) {
        if (sport == null) {
            throw new IllegalArgumentException("Event sport is null");
        }
        try {
            Query query = em.createQuery("SELECT e FROM Event e WHERE sport = :sport");
            query.setParameter("sport", sport);
            return query.getResultList();
        }
        catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Event> findByCity(String city) {
        if (city == null) {
            throw new IllegalArgumentException("Event city is null");
        }
        try {
            Query query = em.createQuery("SELECT e FROM Event e WHERE city = :city");
            query.setParameter("city", city);
            return query.getResultList();
        }
        catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Event> findByAdmin(Sportsman admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Event admin is null");
        }
        try {
            Query query = em.createQuery("SELECT e FROM Event e WHERE admin = :admin");
            query.setParameter("admin", admin);
            return query.getResultList();
        }
        catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Event> findByParticipant(Sportsman participant) {
        if (participant == null) {
            throw new IllegalArgumentException("Event participant is null");
        }
        try {
            Query query = em.createQuery("SELECT e FROM Event e WHERE participant = :participant");
            query.setParameter("participant", participant);
            return query.getResultList();
        }
        catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Event> findAll() {
        try {
            Query query = em.createQuery("SELECT e FROM Event e");
            return query.getResultList();
        }
        catch(NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Event event) {
        em.merge(event);
    }

    @Override
    public void delete(Event event) {
        em.remove(event);
    }
    
}
