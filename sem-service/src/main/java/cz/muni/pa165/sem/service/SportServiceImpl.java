package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportDAO;
import cz.muni.pa165.sem.entity.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return sportDAO.findById(id);
    }

    @Override
    public void create(Sport sport) {
        sportDAO.create(sport);
    }

    @Override
    public void update(Sport sport) {
        sportDAO.update(sport);
    }

    @Override
    public void delete(Sport sport) {
        sportDAO.delete(sport);
    }

    @Override
    public List<Sport> findAll() {
        return sportDAO.findAll();
    }

    @Override
    public Sport findByName(String name) {
        return sportDAO.findByName(name);
    }
}
