package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Result;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;

import java.util.List;

/**
 * The interface ResultDao is used to provide CRUD operations for Result
 *
 * @author Veronika Aksamitova
 */
public interface ResultDAO {

    /**
     * This method puts results object to database
     *
     * @param result the result object to be created
     * @throws IllegalArgumentException When result is {@code null}.
     */
    void create(Result result);

    /**
     * This method finds results object by id
     *
     * @param id id value we want to find
     * @return results with specific id
     * @throws IllegalArgumentException When result id is {@code null}.
     */
    Result findById(Long id);

    /**
     * This method finds results objects by Sportsman
     *
     * @param sportsman the sportsman we want to find results for
     * @return results with specific sportsman
     * @throws IllegalArgumentException When sportsman is {@code null}.
     */
    List<Result> findBySportsman(Sportsman sportsman);

    /**
     * This method finds results objects by Event
     *
     * @param event the event we want to find results for
     * @return results for specific event
     * @throws IllegalArgumentException When event is {@code null}.
     */
    List<Result> findByEvent(Event event);

    /**
     * This method find unique result objects for Event and sportsman
     *
     * @param event the event we want to find results for
     * param sportsman the sportsman we want to find results for
     * @return results for specific event nad sportsman
     * @throws IllegalArgumentException When event is {@code null}.
     */
    Result findBySportsmanAndEvent(Sportsman sportsman, Event event);

    /**
     * This method finds results objects by Sport
     *
     * @param sport the sport object we want to find results for
     * @return results for specific sport
     * @throws IllegalArgumentException When sport is {@code null}.
     */
    List<Result> findBySport(Sport sport);

    /**
     * This method finds results objects by performance
     *
     * @param performance the performance value we want to find
     * @return results with specific performance
     * @throws IllegalArgumentException When performance is {@code null}.
     */
    List<Result> findByPerformance(Double performance);

    /**
     * This method finds results objects by position
     *
     * @param position the position value we want to find
     * @return results with specific position
     * @throws IllegalArgumentException When position is {@code null}.
     */
    List<Result> findByPosition(Integer position);

    /**
     * This method finds result objects by note.
     *
     * @param note the note value we want to find
     * @return results with simmilar note
     * @throws IllegalArgumentException When note is {@code null}.
     */
    List<Result> findByNote(String note);

    /**
     * This method returns all results from database
     *
     * @return all results from database
     */
    List<Result> findAll();

    /**
     * Method which updates result object in DB
     *
     * @param result to be changed
     * @throws IllegalArgumentException When result is {@code null}.
     */
    void update(Result result);

    /**
     * Method which deletes result from DB
     *
     * @param result
     * @throws IllegalArgumentException When result is {@code null}.
     */
    void delete(Result result);

}
