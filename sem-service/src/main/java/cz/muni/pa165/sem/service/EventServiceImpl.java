package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.EventDAO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

/**
 * @author Vit Hovezak
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Override
    public void create(Event event) {
        try {
            eventDAO.create(event);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Failed to create " + event + ", exception: ", e);
        }
    }

    @Override
    public Event findById(Long id) {
        try {
            return eventDAO.findById(id);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find event by id " + id + ", exception: ", e);
        }
    }

    @Override
    public List<Event> findByName(String name) {
        try {
            return eventDAO.findByName(name);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find event by name " + name + ", exception: ", e);
        }
    }

    @Override
    public List<Event> findByDate(Calendar date) {
        try {
            return eventDAO.findByDate(date);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find event by date " + date + ", exception: ", e);
        }
    }

    @Override
    public List<Event> findBySport(Sport sport) {
        try {
            return eventDAO.findBySport(sport);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find event by sport " + sport + ", exception: ", e);
        }
    }

    @Override
    public List<Event> findByCity(String city) {
        try {
            return eventDAO.findByCity(city);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find event by city " + city + ", exception: ", e);
        }
    }

    @Override
    public List<Event> findByAdmin(Sportsman admin) {
        try {
            return eventDAO.findByAdmin(admin);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find event by admin " + admin + ", exception: ", e);
        }
    }

    @Override
    public List<Event> findByParticipant(Sportsman participant) {
        try {
            return eventDAO.findByParticipant(participant);
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find event by participant " + participant + ", exception: ", e);
        }
    }

    @Override
    public List<Event> findAll() {
        try {
            return eventDAO.findAll();
        } catch (Exception e) {
            throw new DataRetrievalFailureException("Failed to find all events, exception: ", e);
        }
    }

    @Override
    public void update(Event event) {
        try {
            eventDAO.update(event);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Failed to update " + event + ", exception: ", e);
        }
    }

    @Override
    public void delete(Event event) {
        try {
            eventDAO.delete(event);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Failed to delete " + event + ", exception: ", e);
        }
    }

}
