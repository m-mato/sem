package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.service.EventService;
import cz.muni.pa165.sem.service.SportService;
import cz.muni.pa165.sem.service.SportsmanService;
import cz.muni.pa165.sem.service.facade.EventFacadeImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Matchers.argThat;

/**
 * @author Vit Hovezak
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class EventFacadeTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private EventFacadeImpl eventFacade = new EventFacadeImpl();

    @Mock
    private EventService eventService;

    @Mock
    private SportService sportService;

    @Mock
    private SportsmanService sportsmanService;

    @Autowired
    private BeanMappingService beanMappingService;

    private Event event;
    private Sport sport;
    private Sportsman sportsman;
    private Calendar date;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        eventFacade.setBeanMappingService(beanMappingService);

        date = Calendar.getInstance();

        sport = new Sport();
        sport.setId(1L);
        sport.setName("Sport 1 name");
        sport.setDescription("Sport 1 description");

        Mockito.when(sportService.findById(1L)).thenReturn(sport);

        sportsman = new Sportsman();
        sportsman.setId(1L);
        sportsman.setName("Sportsman 1 name");
        sportsman.setSurname("Sportsman 1 surname");
        sportsman.setBirthDate(date);
        sportsman.setEmail("Sportsman 1 email");
        sportsman.setPassword("Sportsman 1 password");
        sportsman.setEvents(new HashSet<>());
        sportsman.setInvitations(new HashSet<>());

        Mockito.when(sportsmanService.findById(1L)).thenReturn(sportsman);

        event = new Event();
        event.setName("Event 1 name");
        event.setDescription("Event 1 description");
        event.setDate(date);
        event.setSport(sport);
        event.setCapacity(10);
        event.setCity("Event 1 city");
        event.setAddress("Event 1 address");
        event.setAdmin(sportsman);

        Mockito.when(eventService.findById(1L)).thenReturn(event);
        Mockito.when(eventService.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(eventService.findByName("Event 1 name")).thenReturn(Collections.singletonList(event));
        Mockito.when(eventService.findByName(argThat(not("Event 1 name")))).thenReturn(Collections.emptyList());
        Mockito.when(eventService.findByDate(date)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventService.findByDate(argThat(not(date)))).thenReturn(Collections.emptyList());
        Mockito.when(eventService.findBySport(sport)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventService.findBySport(argThat(not(sport)))).thenReturn(Collections.emptyList());
        Mockito.when(eventService.findByCity("Event 1 city")).thenReturn(Collections.singletonList(event));
        Mockito.when(eventService.findByCity(argThat(not("Event 1 city")))).thenReturn(Collections.emptyList());
        Mockito.when(eventService.findByAdmin(sportsman)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventService.findByAdmin(argThat(not(sportsman)))).thenReturn(Collections.emptyList());
        Mockito.when(eventService.findByParticipant(sportsman)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventService.findByParticipant(argThat(not(sportsman)))).thenReturn(Collections.emptyList());
        Mockito.when(eventService.findAll()).thenReturn(Collections.singletonList(event));
    }

    @Test
    public void testCreate() {
        EventCreateDTO eventCreateDTO = new EventCreateDTO();
        eventCreateDTO.setName("Event 1 name");
        eventCreateDTO.setDescription("Event 1 description");
        eventCreateDTO.setDate(date);
        eventCreateDTO.setSport(beanMappingService.mapTo(sport, SportDTO.class));
        eventCreateDTO.setCapacity(10);
        eventCreateDTO.setCity("Event 1 city");
        eventCreateDTO.setAddress("Event 1 address");
        eventCreateDTO.setAdmin(beanMappingService.mapTo(sportsman, SportsmanDTO.class));

        eventFacade.create(eventCreateDTO);
        Event createdEvent = beanMappingService.mapTo(eventCreateDTO, Event.class);

        Mockito.verify(eventService).create(createdEvent);
    }

    @Test
    public void testFindExistingById() {
        EventDTO eventDTO = eventFacade.findById(1L);
        Event foundEvent = beanMappingService.mapTo(eventDTO, Event.class);

        Mockito.verify(eventService).findById(1L);
        Assert.assertEquals(foundEvent, event);
    }

    @Test
    public void testFindNonExistingById() {
        EventDTO eventDTO = eventFacade.findById(2L);

        Mockito.verify(eventService).findById(2L);
        Assert.assertNull(eventDTO);
    }

    @Test
    public void testFindByName() {
        List<EventDTO> eventDTOs = eventFacade.findByName("Event 1 name");
        Event foundEvent = beanMappingService.mapTo(eventDTOs.get(0), Event.class);

        Mockito.verify(eventService).findByName("Event 1 name");
        Assert.assertNotNull(eventDTOs);
        Assert.assertEquals(eventDTOs.size(), 1);
        Assert.assertEquals(foundEvent, event);
    }

    @Test
    public void testFindByDate() {
        List<EventDTO> eventDTOs = eventFacade.findByDate(date);
        Event foundEvent = beanMappingService.mapTo(eventDTOs.get(0), Event.class);

        Mockito.verify(eventService).findByDate(date);
        Assert.assertNotNull(eventDTOs);
        Assert.assertEquals(eventDTOs.size(), 1);
        Assert.assertEquals(foundEvent, event);
    }

    @Test
    public void testFindBySport() {
        List<EventDTO> eventDTOs = eventFacade.findBySport(sport.getId());
        Event foundEvent = beanMappingService.mapTo(eventDTOs.get(0), Event.class);

        Mockito.verify(eventService).findBySport(sport);
        Assert.assertNotNull(eventDTOs);
        Assert.assertEquals(eventDTOs.size(), 1);
        Assert.assertEquals(foundEvent, event);
    }

    @Test
    public void testFindByCity() {
        List<EventDTO> eventDTOs = eventFacade.findByCity("Event 1 city");
        Event foundEvent = beanMappingService.mapTo(eventDTOs.get(0), Event.class);

        Mockito.verify(eventService).findByCity("Event 1 city");
        Assert.assertNotNull(eventDTOs);
        Assert.assertEquals(eventDTOs.size(), 1);
        Assert.assertEquals(foundEvent, event);
    }

    @Test
    public void testFindByAdmin() {
        List<EventDTO> eventDTOs = eventFacade.findByAdmin(sportsman.getId());
        Event foundEvent = beanMappingService.mapTo(eventDTOs.get(0), Event.class);

        Mockito.verify(eventService).findByAdmin(sportsman);
        Assert.assertNotNull(eventDTOs);
        Assert.assertEquals(eventDTOs.size(), 1);
        Assert.assertEquals(foundEvent, event);
    }

    @Test
    public void testFindByParticipant() {
        List<EventDTO> eventDTOs = eventFacade.findByParticipant(sportsman.getId());
        Event foundEvent = beanMappingService.mapTo(eventDTOs.get(0), Event.class);

        Mockito.verify(eventService).findByParticipant(sportsman);
        Assert.assertNotNull(eventDTOs);
        Assert.assertEquals(eventDTOs.size(), 1);
        Assert.assertEquals(foundEvent, event);
    }

    @Test
    public void testFindAll() {
        List<EventDTO> eventDTOs = eventFacade.findAll();

        Mockito.verify(eventService).findAll();
        Assert.assertNotNull(eventDTOs);
        Assert.assertEquals(eventDTOs.size(), 1);
    }

    @Test
    public void testUpdate() {
        EventUpdateDTO eventUpdateDTO = beanMappingService.mapTo(event, EventUpdateDTO.class);
        eventUpdateDTO.setId(1L);

        eventFacade.update(eventUpdateDTO);

        Mockito.verify(eventService).update(event);
    }

    @Test
    public void testDelete() {
        EventDTO eventDTO = eventFacade.findById(1L);
        eventDTO.setId(1L);

        eventFacade.delete(eventDTO.getId());

        Mockito.verify(eventService).delete(event);
    }

}
