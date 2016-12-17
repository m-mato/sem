package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.ResultDAO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Result;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Veronika Aksamitova
 */
@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultDAO resultDAO;
    
    @Override
    public void create(Result result) {
        resultDAO.create(result);
    }

    @Override
    public void update(Result result) {
        resultDAO.update(result);
    }

    @Override
    public void delete(Result result) {
        resultDAO.delete(result);
    }

    @Override
    public Result findById(Long id) {
        return resultDAO.findById(id);
    }

    @Override
    public List<Result> findAll() {
        List<Result> results = resultDAO.findAll();
        return results == null ? new ArrayList<>() : results;
        
    }

    @Override
    public List<Result> findBySportsman(Sportsman sportsman) { 
        List<Result> results = resultDAO.findBySportsman(sportsman);
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByEvent(Event event) {
        List<Result> results = resultDAO.findByEvent(event);
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public Result findBySportsmanAndEvent(Sportsman sportsman, Event event) {
        return resultDAO.findBySportsmanAndEvent(sportsman, event);
    }

    @Override
    public List<Result> findBySport(Sport sport) {
        List<Result> results = resultDAO.findBySport(sport);
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByPerformance(Double performance) {
        List<Result> results = resultDAO.findByPerformance(performance);
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByPosition(Integer position) {
        List<Result> results = resultDAO.findByPosition(position);
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByNote(String note) {
        List<Result> results = resultDAO.findByNote(note);
        return results == null ? new ArrayList<>() : results;
    }
    
}
