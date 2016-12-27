package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportsmanDAO;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

		if(sportsman == null || sportsman.getPassword() == null || sportsman.getPassword().isEmpty()) {
			throw new IllegalArgumentException("Invalid argument - sportsman");
		}

		sportsman.setPassword(hashPassword(sportsman.getPassword()));
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
	public Sportsman findByEmail(String email) {

		return sportsmanDAO.findByEmail(email);
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
	public List<Sportsman> findBySubstring(String substring, Long event_id) {
		return sportsmanDAO.findBySubstring(substring, event_id);
	}

	@Override
	public void delete(Sportsman sportsman) {

		sportsmanDAO.delete(sportsman);
	}

	private String hashPassword(String password) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(Constants.BC_STRENGTH);
		return passwordEncoder.encode(password);
	}
}
