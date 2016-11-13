package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportsmanDAO;
import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matej Majdis
 */
@Service
public class SportsmanServiceImpl implements SportsmanService {

	@Autowired
	private SportsmanDAO sportsmanDAO;

	@Override
	public void create(Sportsman sportsman) {

		sportsmanDAO.create(sportsman);
	}

	@Override
	public Sportsman findById(Long id) {

		return sportsmanDAO.findById(id);
	}

	@Override
	public List<Sportsman> findByName(String name) {

		return sportsmanDAO.findByName(name);
	}

	@Override
	public List<Sportsman> findBySurname(String surname) {

		return sportsmanDAO.findBySurname(surname);
	}

	@Override
	public List<Sportsman> findAll() {

		return sportsmanDAO.findAll();
	}

	@Override
	public void update(Sportsman sportsman) {

		sportsmanDAO.update(sportsman);
	}

	@Override
	public void delete(Sportsman sportsman) {

		sportsmanDAO.delete(sportsman);
	}

}
