package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sport;

import java.util.List;

/**
 * The interface SportDao is used to provide CRUD operations for Sport entity.
 *
 * @author Kamil Triscik
 */
public interface SportDAO {

    /**
     * Method create new sport.
     *
     * @param sport the sport object to be put into DB
     * @throws IllegalArgumentException When sport is {@code null}.
     */
    void create(Sport sport);

    /**
     * This method finds all sports.
     *
     * @return List of found sport objects.
     */
    List<Sport> findAll();

    /**
     * This method find sport object with specific id.
     *
     * @param id the id value of object we want to find.
     * @return null if object does not exist in DB otherwise return sport object.
     * @throws IllegalArgumentException When sport id is {@code null}.
     */
    Sport findById(Long id);

    /**
     * This method find sport object by name.
     *
     * @param name the name value of object we want to find.
     * @return null if object does not exist in DB otherwise return sport object.
     * @throws IllegalArgumentException When name is {@code null}.
     */
    Sport findByName(String name);

    /**
     * Method update sport object.
     *
     * @param sport the sport object to be updated in DB
     * @throws IllegalArgumentException When sport is {@code null}.
     */
    void update(Sport sport);

    /**
     * Method delete sport object.
     *
     * @param sport the sport object to be dropped drom DB
     * @throws IllegalArgumentException When sport is {@code null}.
     */
    void delete(Sport sport);

}
