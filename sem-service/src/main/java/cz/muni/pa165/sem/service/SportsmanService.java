package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Sportsman;

import java.util.List;

/**
 * @author Matej Majdis
 */
public interface SportsmanService {

	Long create(Sportsman sportsman);

	Sportsman findById(Long id);

	List<Sportsman> findAll();

	void removeSportsman(Sportsman sportsman);

	Long updateSportsman(Sportsman sportsman);
}
