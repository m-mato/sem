package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.EventDAO;
import cz.muni.pa165.sem.dao.InvitationDAO;
import cz.muni.pa165.sem.dao.ResultDAO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Vit Hovezak
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private ResultDAO resultDAO;

    @Autowired
    private InvitationDAO invitationDAO;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void create(Event event) {

        eventDAO.create(event);
    }

    @Override
    public Event findById(Long id) {

        return eventDAO.findById(id);
    }

    @Override
    public List<Event> findByName(String name) {

        return eventDAO.findByName(name);
    }

    @Override
    public List<Event> findByDate(Calendar date) {

        return eventDAO.findByDate(date);
    }

    @Override
    public List<Event> findBySport(Sport sport) {

        return eventDAO.findBySport(sport);
    }

    @Override
    public List<Event> findByCity(String city) {

        return eventDAO.findByCity(city);
    }

    @Override
    public List<Event> findByAdmin(Sportsman admin) {

        return eventDAO.findByAdmin(admin);
    }

    @Override
    public List<Event> findByParticipant(Sportsman participant) {

        return eventDAO.findByParticipant(participant);
    }

    @Override
    public List<Event> findAll() {

        return eventDAO.findAll();
    }

    @Override
    public void update(Event event) {
        Set<Sportsman> participants = new HashSet<>();
        participants.addAll(event.getParticipants());
        eventDAO.update(event);
        notificationService.notifyEventEdited(participants, event);
    }

    @Override
    public void delete(Event event) {
        if (event == null) {
            throw  new IllegalArgumentException("Trying to delete null object");
        }
        if (event.getId() == null) {
            throw  new IllegalArgumentException("Trying to delete in DB non existing event");
        }
        Set<Sportsman> participants = new HashSet<>();
        if (event.getParticipants() != null) {
            participants.addAll(event.getParticipants());
            //notify
            notificationService.notifyEventCanceled(participants, event);

            //next delete results due to foreign constraints
            resultDAO.findByEvent(event).forEach(result -> resultDAO.delete(result));
        }

        //next delete all invitations for this event due to foreign key
        List<Invitation> invitationList = invitationDAO.findByEvent(event);
        if (invitationList != null) {
            for(Invitation invitation : invitationDAO.findByEvent(event)) {
                invitationDAO.delete(invitation);
            }
        }
        eventDAO.delete(event);
    }

    @Override
    public void edit(Event event) {
        Set<Sportsman> participants = new HashSet<>();
        participants.addAll(event.getParticipants());

        eventDAO.update(event);
        notificationService.notifyEventEdited(participants, event);
    }
}
