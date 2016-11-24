package cz.muni.pa165.sem.service.facade;

import cz.muni.pa165.sem.dto.SportCreateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportUpdateDTO;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.facade.SportFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kamil Triscik
 */
@Service
@Transactional
public class SportFacadeImpl implements SportFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private SportService sportService;

    @Override
    public SportDTO create(SportCreateDTO sportCreateDTO) {
        Sport sport = new Sport();
        sport.setName(sportCreateDTO.getName());
        sport.setDescription(sportCreateDTO.getDescription());

        sportService.create(sport);
        return beanMappingService.mapTo(sport, SportDTO.class);
    }

    @Override
    public SportDTO getSportById(Long id) {
        return beanMappingService.mapTo(sportService.findById(id), SportDTO.class);

    }

    @Override
    public SportDTO getSportByName(String name) {
        return beanMappingService.mapTo(sportService.findByName(name), SportDTO.class);
    }

    @Override
    public List<SportDTO> getAllSports() {
        return beanMappingService.mapTo(sportService.findAll(), SportDTO.class);
    }

    @Override
    public void update(SportUpdateDTO sportUpdateDTO) {
        Sport sport = sportService.findById(sportUpdateDTO.getId());
        sport.setName(sportUpdateDTO.getName());
        sport.setDescription(sportUpdateDTO.getDescription());

        sportService.update(sport);
    }

    @Override
    public void delete(Long sportId) {
        sportService.delete(sportService.findById(sportId));
    }

    /**
     * Only for unit tests
     * @param beanMappingService
     */
    public void setBeanMappingService(BeanMappingService beanMappingService) {
        this.beanMappingService = beanMappingService;
    }
}
