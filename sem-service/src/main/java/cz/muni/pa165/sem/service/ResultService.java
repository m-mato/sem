/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    List<Result> getAll();
    
    List<Result> findBySportsman(Sportsman sportsman);

    List<Result> findByEvent(Event event);

    List<Result> findBySport(Sport sport);

    List<Result> findByPerformance(Double performance);

    List<Result> findByPosition(Integer position);

    List<Result> findByNote(String note);
}
