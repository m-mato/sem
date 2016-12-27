package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Sportsman;

import java.util.List;

/**
 * @author Matej Majdis
 */
public interface SportsmanService {

	void create(Sportsman sportsman);

	Sportsman findById(Long id);

	List<Sportsman> findByName(String name);

	List<Sportsman> findBySurname(String surname);

	Sportsman findByEmail(String email);

	List<Sportsman> findAll();

	void delete(Sportsman sportsman);

	void update(Sportsman sportsman);

	List<Sportsman> findBySubstring(String substring, Long event_id);
}
