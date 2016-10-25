package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Sportsman;

import java.util.List;

/**
 * @author Matej Majdis
 */
public interface SportsmanDAO {

	void addSportsman(Sportsman sportsman);

	List<Sportsman> getAll();
}
