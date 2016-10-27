/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Result;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import java.util.List;

/**
 * The interface ResultDao is used to provide CRUD operations for Result
 * 
 * @author xaksamit
 */
public interface ResultDAO {
    
    /**
     * This method puts results object to database
     * 
     * @param result the result object to be created
     */
    void create(Result result);
    
    /**
     * This method finds results object by id
     * 
     * @param id id value we want to find
     * @return results with specific id
     */
    Result findById(Long id);
    
    /**
     * This method finds results objects by Sportsman
     * 
     * @param sportsman the sportsman we want to find results for
     * @return results with specific sportsman
     */
    List<Result> findBySportsman(Sportsman sportsman);
    
    /**
     * This method finds results objects by Event
     * 
     * @param event the event we want to find results for
     * @return results for specific event
     */
    List<Result> findByEvent(Event event);
    
    /**
     * This method finds results objects by Sport
     * 
     * @param sport the sport object we want to find results for
     * @return results for specific sport
     */
    List<Result> findBySport(Sport sport);
    
    /**
     * This method finds results objects by performance
     * 
     * @param performance the performance value we want to find
     * @return results with specific performance
     */
    List<Result> findByPerformance(Double performance);
    
    /**
     * This method finds results objects by position
     * 
     * @param position the position value we want to find
     * @return results with specific position
     */
    List<Result> findByPosition(Integer position);
    
    /**
     * This method finds result objects by note.
     *
     * @param note the note value we want to find
     * @return results with simmilar note
     */
    List<Result> findByNote(String note);
    
    /**
     * This method returns all results from database
     * 
     * @return all results from database
     */
    List<Result> getAll();
    
    /**
     *Method which updates result object in DB
     * 
     * @param result to be changed
     */
    void update(Result result);
    
    /**
     *Method which deletes result from DB
     * 
     * @param result
     */
    void delete(Result result);
    
}
