package cz.muni.pa165.sem.service.facade;

import cz.muni.pa165.sem.dto.EventCreateDTO;
import cz.muni.pa165.sem.dto.EventDTO;
import cz.muni.pa165.sem.dto.EventUpdateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.service.EventService;
import cz.muni.pa165.sem.service.SportService;
import cz.muni.pa165.sem.service.SportsmanService;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vit Hovezak
 */
@Service
@Transactional
public class EventFacadeImpl implements EventFacade {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private SportService sportService;

    @Autowired
    private SportsmanService sportsmanService;

    @Override
    public EventDTO create(EventCreateDTO eventCreateDTO) {
        Event event = new Event();
        event.setName(eventCreateDTO.getName());
        event.setDescription(eventCreateDTO.getDescription());
        event.setDate(eventCreateDTO.getDate());
        SportDTO sportDTO = eventCreateDTO.getSport();
        event.setSport(beanMappingService.mapTo(sportDTO, Sport.class));
        event.setCapacity(eventCreateDTO.getCapacity());
        event.setCity(eventCreateDTO.getCity());
        event.setAddress(eventCreateDTO.getAddress());
        SportsmanDTO sportsmanDTO = eventCreateDTO.getAdmin();
        event.setAdmin(beanMappingService.mapTo(sportsmanDTO, Sportsman.class));
        eventService.create(event);
        return beanMappingService.mapTo(event, EventDTO.class);
    }

    @Override
    public EventDTO findById(Long eventId) {
        Event result = eventService.findById(eventId);
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    @Override
    public List<EventDTO> findByName(String name) {
        List<Event> result = eventService.findByName(name);
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    @Override
    public List<EventDTO> findByDate(Calendar date) {
        List<Event> result = eventService.findByDate(date);
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    @Override
    public List<EventDTO> findBySport(Long sportId) {
        Sport sport = sportService.findById(sportId);
        List<Event> result = eventService.findBySport(sport);
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    @Override
    public List<EventDTO> findByCity(String city) {
        List<Event> result = eventService.findByCity(city);
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    @Override
    public List<EventDTO> findByAdmin(Long adminId) {
        Sportsman sportsman = sportsmanService.findById(adminId);
        List<Event> result = eventService.findByAdmin(sportsman);
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    // TODO: 15-Dec-16 bad preco je tam Id
    @Override
    public List<EventDTO> findByParticipant(Long participantId) {
        Sportsman sportsman = sportsmanService.findById(participantId);
        List<Event> result = eventService.findByParticipant(sportsman);
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    @Override
    public List<EventDTO> findAll() {
        List<Event> result = eventService.findAll();
        return beanMappingService.mapTo(result, EventDTO.class);
    }

    @Override
    public void update(EventUpdateDTO eventUpdateDTO) {
        Event event = eventService.findById(eventUpdateDTO.getId());
        event.setName(eventUpdateDTO.getName());
        event.setDescription(eventUpdateDTO.getDescription());
        event.setDate(eventUpdateDTO.getDate());
        SportDTO sportDTO = eventUpdateDTO.getSport();
        event.setSport(beanMappingService.mapTo(sportDTO, Sport.class));
        event.setCapacity(eventUpdateDTO.getCapacity());
        event.setCity(eventUpdateDTO.getCity());
        event.setAddress(eventUpdateDTO.getAddress());
        SportsmanDTO sportsmanDTO = eventUpdateDTO.getAdmin();
        event.setAdmin(beanMappingService.mapTo(sportsmanDTO, Sportsman.class));
        eventService.update(event);
    }

    @Override
    public void delete(Long eventId) {
        Event event = eventService.findById(eventId);
        eventService.delete(event);
    }

    /**
     * Only for unit tests
     * @param beanMappingService
     */
    public void setBeanMappingService(BeanMappingService beanMappingService) {
        this.beanMappingService = beanMappingService;
    }

}
