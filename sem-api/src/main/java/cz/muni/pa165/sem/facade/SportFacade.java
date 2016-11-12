package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.SportCreateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportUpdateDTO;

import java.util.List;

/**
 * @author Kamil Triscik
 */
public interface SportFacade {

    SportDTO createSport(SportCreateDTO sportCreateDTO);

    SportDTO getSportById(Long id);

    SportDTO getSportByName(String name);

    List<SportDTO> getAllSports();

    void updateSport(SportUpdateDTO sportUpdateDTO);

    void deleteSport(Long sportId);
}
