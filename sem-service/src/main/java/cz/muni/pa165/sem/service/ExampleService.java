package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportsmanDAO;
import cz.muni.pa165.sem.entity.Sportsman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matej Majdis
 */
@Service
public class ExampleService {

	@Autowired
	private SportsmanDAO sportsmanDAO;

	@Transactional
	public List<String> getAllNames() {

		List<String> result = new ArrayList<>();
		sportsmanDAO.findAll().forEach(item -> result.add(item.getName()));
		return result;
	}

	@Transactional
	public void addSportsman(Sportsman sportsman) {

		sportsmanDAO.create(sportsman);
	}
}
