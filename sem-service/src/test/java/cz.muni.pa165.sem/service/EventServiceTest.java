package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.*;
import cz.muni.pa165.sem.entity.*;

import java.util.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.not;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class EventServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private EventService eventService = new EventServiceImpl();

    @Mock
    private EventDAO eventDAO;

    private Event event;
    private Sport sport;
    private Sportsman sportsman;
    private Calendar date;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sportsman = new Sportsman();
        sportsman.setId(1L);
        sportsman.setName("Sportsman");
        sportsman.setSurname("Surname");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail("sportsman@email.com");
        sportsman.setPassword("pass");
        sportsman.setEvents(new HashSet<>());
        sportsman.setInvitations(new HashSet<>());


        sport = new Sport();
        sport.setName("sport");
        sport.setDescription("desc");


        event = new Event();
        String eventName = "ev";
        event.setName(eventName);
        String description = "New event= name";
        event.setDescription(description);
        date = new GregorianCalendar(2016,1,31);
        event.setDate(date);
        event.setAdmin(sportsman);
        Integer capacity = 5;
        event.setCapacity(capacity);
        event.setSport(sport);
        String address = "Address";
        event.setAddress(address);
        String city = "city";
        event.setCity(city);
        event.addParticipant(sportsman);

        Mockito.when(eventDAO.findById(1L)).thenReturn(event);
        Mockito.when(eventDAO.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(eventDAO.findByName("ev")).thenReturn(Collections.singletonList(event));
        Mockito.when(eventDAO.findByName(argThat(not("ev")))).thenReturn(new ArrayList<>());
        Mockito.when(eventDAO.findBySport(sport)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventDAO.findBySport(argThat(not(sport)))).thenReturn(new ArrayList<>());
        Mockito.when(eventDAO.findByCity("ev")).thenReturn(Collections.singletonList(event));
        Mockito.when(eventDAO.findByCity(argThat(not("ev")))).thenReturn(new ArrayList<>());
        Mockito.when(eventDAO.findByAdmin(sportsman)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventDAO.findByAdmin(argThat(not(sportsman)))).thenReturn(new ArrayList<>());
        Mockito.when(eventDAO.findByParticipant(sportsman)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventDAO.findByParticipant(argThat(not(sportsman)))).thenReturn(new ArrayList<>());
        Mockito.when(eventDAO.findByDate(date)).thenReturn(Collections.singletonList(event));
        Mockito.when(eventDAO.findByDate(argThat(not(date)))).thenReturn(new ArrayList<>());
        Mockito.when(eventDAO.findAll()).thenReturn(Collections.singletonList(event));

    }

    @Test
    public void testCreate() {
        eventService.create(event);

        Mockito.verify(eventDAO, times(1)).create(event);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
        doThrow(new IllegalArgumentException("Trying to createEvent null object!"))
                .when(eventDAO)
                .create(null);

        eventService.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUninitializedObject() {
        Event event1 = new Event();
        doThrow(new IllegalArgumentException("Trying to createEvent uninitialized object!"))
                .when(eventDAO)
                .create(event1);

        eventService.create(event1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAlreadyExisted() {
        doThrow(new IllegalArgumentException("Trying to createEvent already existing object!"))
                .when(eventDAO)
                .create(this.event);

        eventService.create(event);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testUpdate() {
        eventService.update(event);
        Mockito.verify(eventDAO, times(1)).update(event);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testUpdateNull() {
        doThrow(new IllegalArgumentException("Trying to updateEvent null object!"))
                .when(eventDAO)
                .update(null);

        eventService.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDelete() {
        eventService.delete(event);
        Mockito.verify(eventDAO, times(1)).delete(event);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNotExistingObject() {
        doThrow(new IllegalArgumentException("Trying to deleteEvent not existing object!"))
                .when(eventDAO)
                .delete(new Event());

        eventService.delete(new Event());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        doThrow(new IllegalArgumentException("Trying to deleteEvent null object!"))
                .when(eventDAO)
                .delete(null);

        eventService.delete(null);
    }

    @Test
    public void testFindExistingById() {
        Long id = 1L;
        Event event = eventService.findById(id);
        Assert.assertNotNull(event);
        Mockito.verify(eventDAO, times(1)).findById(id);
    }

    @Test
    public void testFindNonExistingById() {
        Long id = 2L;
        Event event1 = eventService.findById(id);
        Assert.assertNull(event1);
        Mockito.verify(eventDAO, times(1)).findById(id);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(eventDAO)
                .findById(null);

        eventService.findById(null);
    }

    @Test
    public void testFindExistingByName() {
        String name  = "ev";
        List<Event> events = eventService.findByName(name);
        Assert.assertNotNull(events);
        Mockito.verify(eventDAO, times(1)).findByName(name);

    }

    @Test
    public void testFindNonExistingByName() {
        String name  = "ev1";
        List<Event> events = eventService.findByName(name);
        Assert.assertTrue(events.isEmpty());
        Mockito.verify(eventDAO, times(1)).findByName(name);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullName() {
        doThrow(new IllegalArgumentException("Trying to find object by null name!"))
                .when(eventDAO)
                .findByName(null);

        eventService.findByName(null);
    }

    @Test
    public void testFindExistingBySport() {
        List<Event> events = eventService.findBySport(sport);
        Assert.assertNotNull(events);
        Assert.assertEquals(events.size(),1);
        Mockito.verify(eventDAO, times(1)).findBySport(sport);
    }

    @Test
    public void testFindNonExistingBySport() {
        Sport sport1 = new Sport();
        List<Event> events = eventService.findBySport(sport1);
        Assert.assertNotNull(events);
        Assert.assertTrue(events.isEmpty());
        Mockito.verify(eventDAO, times(1)).findBySport(sport1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullSport() {
        doThrow(new IllegalArgumentException("Trying to find object by null sport!"))
                .when(eventDAO)
                .findBySport(null);

        eventService.findBySport(null);
    }

    @Test
    public void testFindExistingByCity() {
        String city  = "city";
        List<Event> events = eventService.findByCity(city);
        Assert.assertNotNull(events);
        Mockito.verify(eventDAO, times(1)).findByCity(city);

    }

    @Test
    public void testFindNonExistingByCity() {
        String city  = "city";
        List<Event> events = eventService.findByCity(city);
        Assert.assertTrue(events.isEmpty());
        Mockito.verify(eventDAO, times(1)).findByCity(city);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullCity() {
        doThrow(new IllegalArgumentException("Trying to find object by null city name!"))
                .when(eventDAO)
                .findByCity(null);

        eventService.findByCity(null);
    }

    @Test
    public void testFindExistingByAdmin() {
        List<Event> events = eventService.findByAdmin(sportsman);
        Assert.assertNotNull(events);
        Assert.assertEquals(events.size(),1);
        Mockito.verify(eventDAO, times(1)).findByAdmin(sportsman);
    }

    @Test
    public void testFindNonExistingByAdmin() {
        Sportsman admin = new Sportsman();
        List<Event> events = eventService.findByAdmin(admin);
        Assert.assertNotNull(events);
        Assert.assertTrue(events.isEmpty());
        Mockito.verify(eventDAO, times(1)).findByAdmin(admin);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullAdmin() {
        doThrow(new IllegalArgumentException("Trying to find object by null admin!"))
                .when(eventDAO)
                .findByAdmin(null);

        eventService.findByAdmin(null);
    }

    @Test
    public void testFindExistingByParticipant() {
        List<Event> events = eventService.findByParticipant(sportsman);
        Assert.assertNotNull(events);
        Assert.assertEquals(events.size(),1);
        Mockito.verify(eventDAO, times(1)).findByParticipant(sportsman);
    }

    @Test
    public void testFindNonExistingByParticipant() {
        Sportsman participant = new Sportsman();
        List<Event> events = eventService.findByParticipant(participant);
        Assert.assertNotNull(events);
        Assert.assertTrue(events.isEmpty());
        Mockito.verify(eventDAO, times(1)).findByParticipant(participant);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullParticipant() {
        doThrow(new IllegalArgumentException("Trying to find object by null participant!"))
                .when(eventDAO)
                .findByParticipant(null);

        eventService.findByParticipant(null);
    }

    @Test
    public void testFindExistingByDate() {
        List<Event> events = eventService.findByDate(date);
        Assert.assertNotNull(events);
        Assert.assertEquals(events.size(),1);
        Mockito.verify(eventDAO, times(1)).findByDate(date);
    }

    @Test
    public void testFindNonExistingByDate() {
        Calendar cal = new GregorianCalendar(2015,1,31);
        List<Event> events = eventService.findByDate(cal);
        Assert.assertNotNull(events);
        Assert.assertTrue(events.isEmpty());
        Mockito.verify(eventDAO, times(1)).findByDate(cal);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullDate() {
        doThrow(new IllegalArgumentException("Trying to find object by null participant!"))
                .when(eventDAO)
                .findByDate(null);

        eventService.findByDate(null);
    }

    @Test
    public void testFindAll() {
        List<Event> events = eventService.findAll();
        Assert.assertNotNull(events);
        Assert.assertEquals(events.size(),1);
        Mockito.verify(eventDAO, times(1)).findAll();
    }
}
