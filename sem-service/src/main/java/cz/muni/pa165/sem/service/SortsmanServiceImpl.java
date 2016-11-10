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
public class SortsmanServiceImpl implements SportsmanService {

	@Autowired
	SportsmanDAO sportsmanDAO;

	@Override
	public Long create(Sportsman sportsman) {

		sportsmanDAO.create(sportsman);
		return sportsman.getId();
	}

	@Override
	public Sportsman findById(Long id) {

		return sportsmanDAO.findById(id);
	}

	@Override
	public List<Sportsman> findAll() {

		return sportsmanDAO.findAll();
	}

	@Override
	public void removeSportsman(Sportsman sportsman) {

		sportsmanDAO.delete(sportsman);
	}

	@Override
	public Long updateSportsman(Sportsman sportsman) {

		sportsmanDAO.update(sportsman);
		return sportsman.getId();
	}

}
