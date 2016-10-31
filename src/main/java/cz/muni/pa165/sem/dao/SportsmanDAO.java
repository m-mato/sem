package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sportsman;

import java.util.List;

/**
 * @author Matej Majdis
 */
public interface SportsmanDAO {

	/**
	 * Method create new sportsman.
	 *
	 * @param sportsman the sportsman object to be put into DB
	 * @throws IllegalArgumentException When sportsman is {@code null}.
	 */
	void create(Sportsman sportsman);

	/**
	 * This method find sportsman object with specific id.
	 *
	 * @param id the id value of object we want to find.
	 * @return null if object does not exist in DB otherwise return sportsman object.
	 * @throws IllegalArgumentException When id is {@code null}.
	 */
	Sportsman findById(Long id);

	/**
	 * This method find sportsman by name.
	 *
	 * @param name the name value of object we want to find.
	 * @return null if object does not exist in DB otherwise return sportsman object.
	 * @throws IllegalArgumentException When name is {@code null}.
	 */
	List<Sportsman> findByName(String name);

	/**
	 * This method find sportsman by surname.
	 *
	 * @param surname the surname value of object we want to find.
	 * @return null if object does not exist in DB otherwise return sportsman object.
	 * @throws IllegalArgumentException When surname is {@code null}.
	 */
	List<Sportsman> findBySurname(String surname);

	/**
	 * Method update sportsman object.
	 *
	 * @param sportsman the sportsman object to be updated in DB
	 * @throws IllegalArgumentException When sportsman is {@code null}.
	 */
	void update(Sportsman sportsman);

	/**
	 * Method delete sportsman object.
	 *
	 * @param sportsman the sportsman object to be dropped drom DB
	 * @throws IllegalArgumentException When sportsman is {@code null}.
	 */
	void delete(Sportsman sportsman);

	/**
	 * This method finds all sportsmans.
	 *
	 * @return List of found sportsman objects.
	 */
	List<Sportsman> findAll();
}
