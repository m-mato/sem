package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.SportsmanCreateDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.dto.SportsmanUpdateDTO;

import java.util.List;

/**
 * @author Matej Majdis
 */
public interface SportsmanFacade {

	SportsmanDTO create(SportsmanCreateDTO sportCreateDTO);

	SportsmanDTO getById(Long id);

	List<SportsmanDTO> getByName(String name);

	List<SportsmanDTO> getBySurname(String surname);

	SportsmanDTO getByEmail(String email);

	List<SportsmanDTO> getAll();

	void update(SportsmanUpdateDTO sportsmanUpdateDTO);

	void delete(Long id);

	List<SportsmanDTO> findBySubstring(String substring, Long event_id);
}
