package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;

import java.util.Calendar;
import java.util.List;

/**
 * The interface EventDAO is used to provide CRUD operations for Event entity.
 *
 * @author Vit Hovezak
 */
public interface EventDAO {

    /**
     * This method creates new event.
     *
     * @param event The event object to be created.
     * @throws IllegalArgumentException When event is {@code null}.
     */
    void create(Event event);

    /**
     * This method finds event with specific id.
     *
     * @param id The event id.
     * @return Found event object or {@code null} otherwise.
     * @throws IllegalArgumentException When id is {@code null}.
     */
    Event findById(Long id);

    /**
     * This method finds events with specific name.
     *
     * @param name The event name.
     * @return List of found event objects.
     * @throws IllegalArgumentException When name is {@code null}.
     */
    List<Event> findByName(String name);

    /**
     * This method finds events with specific date.
     *
     * @param date The event date.
     * @return List of found event objects.
     * @throws IllegalArgumentException When date is {@code null}.
     */
    List<Event> findByDate(Calendar date);

    /**
     * This method finds events with specific sport.
     *
     * @param sport The event sport.
     * @return List of found event objects.
     * @throws IllegalArgumentException When sport is {@code null}.
     */
    List<Event> findBySport(Sport sport);

    /**
     * This method finds events with specific city.
     *
     * @param city The event city.
     * @return List of found event objects.
     * @throws IllegalArgumentException When city is {@code null}.
     */
    List<Event> findByCity(String city);

    /**
     * This method finds events with specific admin.
     *
     * @param admin The event admin.
     * @return List of found event objects.
     * @throws IllegalArgumentException When admin is {@code null}.
     */
    List<Event> findByAdmin(Sportsman admin);

    /**
     * This method finds events with specific participant.
     *
     * @param participant The event participant.
     * @return List of found event objects.
     * @throws IllegalArgumentException When participant is {@code null}.
     */
    List<Event> findByParticipant(Sportsman participant);

    /**
     * This method finds all events.
     *
     * @return List of found event objects.
     */
    List<Event> findAll();

    /**
     * This method updates event.
     *
     * @param event The event object.
     * @throws IllegalArgumentException When event is {@code null}.
     */
    void update(Event event);

    /**
     * This method deletes event.
     *
     * @param event The event object.
     * @throws IllegalArgumentException When event is {@code null}.
     */
    void delete(Event event);

}
