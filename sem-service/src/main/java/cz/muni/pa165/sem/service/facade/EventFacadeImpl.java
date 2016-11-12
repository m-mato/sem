package cz.muni.pa165.sem.service.facade;

import cz.muni.pa165.sem.dto.EventCreateDTO;
import cz.muni.pa165.sem.dto.EventDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.service.EventService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vit Hovezak
 */
@Transactional
public class EventFacadeImpl implements EventFacade {

    @Autowired
    private EventService eventService;

    @Override
    public void create(EventCreateDTO event) {
        // TODO
    }

    @Override
    public EventDTO findById(Long id) {
        // TODO
        return null;
    }

    @Override
    public List<EventDTO> findByName(String name) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<EventDTO> findByDate(Calendar date) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<EventDTO> findBySport(SportDTO sport) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<EventDTO> findByCity(String city) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<EventDTO> findByAdmin(SportsmanDTO admin) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<EventDTO> findByParticipant(SportsmanDTO participant) {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<EventDTO> findAll() {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public void update(EventDTO event) {
        // TODO
    }

    @Override
    public void delete(EventDTO event) {
        // TODO
    }

}
