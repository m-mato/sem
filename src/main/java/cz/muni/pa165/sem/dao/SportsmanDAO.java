package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sportsman;

import java.util.List;

/**
 * @author Matej Majdis
 */
public interface SportsmanDAO {

	void create(Sportsman sportsman);

	Sportsman findById(Long id);

	List<Sportsman> findByName(String name);

	List<Sportsman> findBySurname(String surname);

	void update(Sportsman sportsman);

	void delete(Sportsman sportsman);

	List<Sportsman> getAll();
}
