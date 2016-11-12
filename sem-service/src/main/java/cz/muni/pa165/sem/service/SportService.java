package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Sport;

import java.util.List;

/**
 * @author Kamil Triscik
 */
public interface SportService {

    Sport findById(Long id);

    void create(Sport sport);

    void update(Sport sport);

    void delete(Sport sport);

    List<Sport> findAll();

    Sport findByName(String name);

}
