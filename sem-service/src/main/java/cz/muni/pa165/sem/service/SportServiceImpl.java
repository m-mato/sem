package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportDAO;
import cz.muni.pa165.sem.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kamil Triscik
 */
@Service
public class SportServiceImpl implements SportService {

    @Autowired
    private SportDAO sportDAO;

    @Override
    public Sport findById(Long id) {
        Sport sport;
        try {
            sport = sportDAO.findById(id);
        } catch (Exception ex) {
            throw new DataRetrievalFailureException("Failed to find Sport with id \"" + id + "\", exception \n" , ex);
        }
        return sport;
    }

    @Override
    public void create(Sport sport) {
        try {
            sportDAO.create(sport);
        } catch (Exception ex) {
            throw new DataIntegrityViolationException("Failed to create Sport \"" + sport + "\", exception: \n" , ex);
        }
    }

    @Override
    public void update(Sport sport) {
        try {
            sportDAO.update(sport);
        } catch (Exception ex) {
            throw new DataIntegrityViolationException("Failed to update Sport \"" + sport + "\", exception: \n" , ex);
        }
    }

    @Override
    public void delete(Sport sport) {
        try {
            sportDAO.delete(sport);
        } catch (Exception ex) {
            throw new DataIntegrityViolationException("Failed to delete Sport \"" + sport + "\", exception: \n" , ex);
        }
    }

    @Override
    public List<Sport> findAll() {
        List<Sport> resultList;

        try {
            resultList = sportDAO.findAll();
        } catch (Exception ex) {
            throw new DataRetrievalFailureException("Failed to find all Sports, exception: \n" , ex);
        }
        return resultList == null ? new ArrayList<>() : resultList;
    }

    @Override
    public Sport findByName(String name) {
        Sport sport;

        try {
            sport = sportDAO.findByName(name);
        } catch (Exception ex) {
            throw new DataRetrievalFailureException("Failed to find Sport by name \"" + name + "\", exception: \n" , ex);
        }
        return sport;
    }
}
