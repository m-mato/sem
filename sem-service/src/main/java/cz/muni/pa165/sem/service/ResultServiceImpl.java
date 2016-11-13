package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.ResultDAO;
import cz.muni.pa165.sem.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;

/**
 *
 * @author Veronika Aksamitova
 */
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultDAO resultDAO;
    
    @Override
    public void create(Result result) {
        try{
            resultDAO.create(result);
        }catch(Exception ex){
            throw new DataIntegrityViolationException("Failed to create Result " + result + ", exception\n" + ex);
        }
    }

    @Override
    public void update(Result result) {
        try{
            resultDAO.update(result);
        }catch(Exception ex){
            throw new DataIntegrityViolationException("Failed to update Result " + result + ", exception\n" + ex);
        }
    }

    @Override
    public void delete(Result result) {try{
            resultDAO.delete(result);
        }catch(Exception ex){
            throw new DataIntegrityViolationException("Failed to delete Result " + result + ", exception\n" + ex);
        }
    }

    @Override
    public Result findById(Long id) {
        Result result;
        try {
            result = resultDAO.findById(id);
        } catch (Exception ex) {
            throw new DataRetrievalFailureException("Failed to find Result with id " + id + " exception \n" , ex);
        }
        return result;
    }

    @Override
    public List<Result> getAll() {
        List<Result> results;
        
        try{
            results = resultDAO.getAll();
        }catch(Exception ex){
            throw new DataRetrievalFailureException("Failed to get all Results, exception \n" + ex);
        }
        return results == null ? new ArrayList<>() : results;
        
    }

    @Override
    public List<Result> findBySportsman(Sportsman sportsman) { 
        List<Result> results;
        
        try{
            results = resultDAO.findBySportsman(sportsman);
        }catch(Exception ex){
            throw new DataRetrievalFailureException("Failed to find Results for sportsman " + sportsman + ", exception \n" + ex);
        }
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByEvent(Event event) {
        List<Result> results;
        
        try{
            results = resultDAO.findByEvent(event);
        }catch(Exception ex){
            throw new DataRetrievalFailureException("Failed to find Results for event " + event + ", exception \n" + ex);
        }
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findBySport(Sport sport) {
        List<Result> results;
        
        try{
            results = resultDAO.findBySport(sport);
        }catch(Exception ex){
            throw new DataRetrievalFailureException("Failed to find Results for sport" + sport + ", exception \n" + ex);
        }
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByPerformance(Double performance) {
        List<Result> results;
        
        try{
            results = resultDAO.findByPerformance(performance);
        }catch(Exception ex){
            throw new DataRetrievalFailureException("Failed to find Results with performnace " + performance + ", exception \n" + ex);
        }
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByPosition(Integer position) {
        List<Result> results;
        
        try{
            results = resultDAO.findByPosition(position);
        }catch(Exception ex){
            throw new DataRetrievalFailureException("Failed to find Results for position " + position + ", exception \n" + ex);
        }
        return results == null ? new ArrayList<>() : results;
    }

    @Override
    public List<Result> findByNote(String note) {
        List<Result> results;
        
        try{
            results = resultDAO.findByNote(note);
        }catch(Exception ex){
            throw new DataRetrievalFailureException("Failed to find Results with note " + note + ", exception \n" + ex);
        }
        return results == null ? new ArrayList<>() : results;
    
    }
    
}
