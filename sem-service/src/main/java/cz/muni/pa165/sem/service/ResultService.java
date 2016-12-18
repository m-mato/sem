package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.*;
import java.util.List;

/**
 *
 * @author Veronika Aksamitova
 */
public interface ResultService {
    
    void create(Result result);
    
    void update(Result result);

    void delete(Result result); 
    
    Result findById(Long id);
    
    List<Result> findAll();
    
    List<Result> findBySportsman(Sportsman sportsman);

    List<Result> findByEvent(Event event);

    Result findBySportsmanAndEvent(Sportsman sportsman, Event event);

    List<Result> findBySport(Sport sport);

    List<Result> findByPerformance(Double performance);

    List<Result> findByPosition(Integer position);

    List<Result> findByNote(String note);
}
