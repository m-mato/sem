package cz.muni.pa165.sem.service.facade;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.service.SportsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Matej Majdis
 */
@Service
@Transactional
public class SportsmanFacadeImpl implements SportsmanFacade {

	@Autowired
	private BeanMappingService beanMappingService;

	@Autowired
	private SportsmanService sportsmanService;

	@Override
	public SportsmanDTO create(SportsmanCreateDTO sportCreateDTO) {

		Sportsman sportsman = new Sportsman();
		sportsman.setName(sportCreateDTO.getName());
		sportsman.setSurname(sportCreateDTO.getSurname());
		sportsman.setBirthDate(sportCreateDTO.getBirthDate());
		sportsman.setEmail(sportCreateDTO.getEmail());
		sportsman.setPassword(sportCreateDTO.getPassword()); //TODO : Hash

		sportsmanService.create(sportsman);
		return beanMappingService.mapTo(sportsman, SportsmanDTO.class);
	}

	@Override
	public SportsmanDTO getById(Long id) {
		return beanMappingService.mapTo(sportsmanService.findById(id), SportsmanDTO.class);
	}

	@Override
	public List<SportsmanDTO> getByName(String name) {
		return beanMappingService.mapTo(sportsmanService.findByName(name), SportsmanDTO.class);
	}

	@Override
	public List<SportsmanDTO> getBySurname(String surname) {
		return beanMappingService.mapTo(sportsmanService.findByName(surname), SportsmanDTO.class);
	}

	@Override
	public List<SportsmanDTO> getAll() {
		return beanMappingService.mapTo(sportsmanService.findAll(), SportsmanDTO.class);
	}

	@Override
	public void update(SportsmanUpdateDTO sportsmanUpdateDTO) {

		Sportsman sportsman = new Sportsman();
		sportsman.setName(sportsmanUpdateDTO.getName());
		sportsman.setSurname(sportsmanUpdateDTO.getSurname());
		sportsman.setBirthDate(sportsmanUpdateDTO.getBirthDate());
		sportsman.setEmail(sportsmanUpdateDTO.getEmail());
		sportsman.setPassword(sportsmanUpdateDTO.getPassword()); //TODO : Hash

		sportsmanService.update(sportsman);
	}

	@Override
	public void delete(Long id) {
		sportsmanService.delete(sportsmanService.findById(id));
	}
}
