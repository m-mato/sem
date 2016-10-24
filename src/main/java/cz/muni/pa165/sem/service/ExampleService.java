package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.repository.SportsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matej Majdis
 */
@Service
public class ExampleService {

	@Autowired
	private SportsmanRepository sportsmanRepository;

	@Transactional
	public List<String> getAllNames() {
		List<String> result = new ArrayList<>();
		sportsmanRepository.findAll().forEach(item -> result.add(item.getName()));
		return result;
	}

	@Transactional
	public void addSportsman(Sportsman sportsman) {
		sportsmanRepository.save(sportsman);
	}
}
