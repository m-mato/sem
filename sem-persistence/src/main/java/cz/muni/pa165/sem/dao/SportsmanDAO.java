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
	 * This method find sportsman by email.
	 *
	 * @param email the email value of object we want to find.
	 * @return null if object does not exist in DB otherwise return sportsman object.
	 * @throws IllegalArgumentException When email is {@code null}.
	 * @throws IllegalArgumentException When email is not unique.
	 */
	Sportsman findByEmail(String email);

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

	/**
	 * This method find sportsman which name, surname or email contain substring
	 *
	 * @param substring the substring value of objects we want to find.
	 * @param event_id (optional) id of event we are looking new available sportsmans(no enrolled in event with id event_id)
	 * @return null if object does not exist in DB otherwise return sportsman object.
	 * @throws IllegalArgumentException When surname is {@code null}.
	 */
	List<Sportsman> findBySubstring(String substring, Long event_id);
}
