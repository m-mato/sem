package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.dao.SportsmanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
		return null;
	}

	@Transactional
	public void addSportsman(Sportsman sportsman) {
	}
}
