package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.EventDTO;
import cz.muni.pa165.sem.dto.ResultCreateDTO;
import cz.muni.pa165.sem.dto.ResultDTO;
import cz.muni.pa165.sem.dto.ResultUpdateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import java.util.List;

/**
 *
 * @author Veronika Aksamitova
 */
public interface ResultFacade {
    /**
     * This method puts results object to database
     *
     * @param result the result object to be created
     * @return result
     * @throws IllegalArgumentException When result is {@code null}.
     */
    ResultDTO create(ResultCreateDTO result);

    /**
     * This method finds results object by id
     *
     * @param id id value we want to find
     * @return results with specific id
     * @throws IllegalArgumentException When result id is {@code null}.
     */
    ResultDTO findById(Long id);

    /**
     * This method finds results objects by Sportsman
     *
     * @param sportsman the sportsman we want to find results for
     * @return results with specific sportsman
     * @throws IllegalArgumentException When sportsman is {@code null}.
     */
    List<ResultDTO> findBySportsman(SportsmanDTO sportsman);

    /**
     * This method finds results objects by Event
     *
     * @param event the event we want to find results for
     * @return results for specific event
     * @throws IllegalArgumentException When event is {@code null}.
     */
    List<ResultDTO> findByEvent(EventDTO event);

    /**
     * This method find unique result objects for Event and sportsman
     *
     * @param event the event we want to find results for
     * param sportsman the sportsman we want to find results for
     * @return results for specific event nad sportsman
     * @throws IllegalArgumentException When event is {@code null}.
     */
    ResultDTO findBySportsmanAndEvent(SportsmanDTO sportsman, EventDTO event);

    /**
     * This method finds results objects by Sport
     *
     * @param sport the sport object we want to find results for
     * @return results for specific sport
     * @throws IllegalArgumentException When sport is {@code null}.
     */
    List<ResultDTO> findBySport(SportDTO sport);

    /**
     * This method finds results objects by performance
     *
     * @param performance the performance value we want to find
     * @return results with specific performance
     * @throws IllegalArgumentException When performance is {@code null}.
     */
    List<ResultDTO> findByPerformance(Double performance);

    /**
     * This method finds results objects by position
     *
     * @param position the position value we want to find
     * @return results with specific position
     * @throws IllegalArgumentException When position is {@code null}.
     */
    List<ResultDTO> findByPosition(Integer position);

    /**
     * This method finds result objects by note.
     *
     * @param note the note value we want to find
     * @return results with simmilar note
     * @throws IllegalArgumentException When note is {@code null}.
     */
    List<ResultDTO> findByNote(String note);

    /**
     * This method returns all results from database
     *
     * @return all results from database
     */
    List<ResultDTO> findAll();

    /**
     * Method which updates result object in DB
     *
     * @param result to be changed
     * @throws IllegalArgumentException When result is {@code null}.
     */
    void update(ResultUpdateDTO result);

    /**
     * Method which deletes result from DB
     *
     * @param  resultId
     * @throws IllegalArgumentException When result is {@code null}.
     */
    void delete(Long resultId);
}
