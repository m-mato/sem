package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.EventCreateDTO;
import cz.muni.pa165.sem.dto.EventDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import java.util.Calendar;
import java.util.List;

/**
 * @author Vit Hovezak
 */
public interface EventFacade {

    void create(EventCreateDTO event);

    EventDTO findById(Long id);

    List<EventDTO> findByName(String name);

    List<EventDTO> findByDate(Calendar date);

    List<EventDTO> findBySport(SportDTO sport);

    List<EventDTO> findByCity(String city);

    List<EventDTO> findByAdmin(SportsmanDTO admin);

    List<EventDTO> findByParticipant(SportsmanDTO participant);

    List<EventDTO> findAll();

    void update(EventDTO event);

    void delete(EventDTO event);

}
