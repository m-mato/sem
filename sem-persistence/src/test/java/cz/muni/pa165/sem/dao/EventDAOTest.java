package cz.muni.pa165.sem.dao;


import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Kamil Triscik
 */
@Transactional
@ContextConfiguration(locations = "classpath:dao-context.xml")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class EventDAOTest extends AbstractTestNGSpringContextTests {

    private List<Sportsman> sportsmans;
    private List<Sport> sports;
    private final int eventsCount = 2;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private SportsmanDAO sportsmanDAO;

    @Autowired
    private SportDAO sportDAO;

    // TODO: 28-Oct-16 try mocking??? 
    
    @BeforeMethod
    public void setUp() throws Exception {
        sportsmans = this.generateSportmans(7);
        sports = this.generateSports(2);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Event ev = getEvent("existingEvent",sportsmans.get(0));
        ev.setDate(cal);
        ev.addParticipant(sportsmans.get(0));
        ev.addParticipant(sportsmans.get(1));
        ev.addParticipant(sportsmans.get(2));
        eventDAO.create(ev);

        for (int i = 0; i < eventsCount; i++) {
            eventDAO.create(getEvent("event" + (i+1),sportsmans.get(1)));
        }
        ev = getEvent("event"+(eventsCount+1),sportsmans.get(0), sports.get(1));
        ev.addParticipant(sportsmans.get(2));
        ev.addParticipant(sportsmans.get(3));
        eventDAO.create(ev);
    }

    @AfterClass
    public void tearDown() throws Exception {
        List<Event> events = eventDAO.findAll();
        events.forEach(eventDAO::delete);
    }

    @Test
    public void createValidEvent() throws Exception {
        Event event = getEvent("newEvent", sportsmans.get(0));
        eventDAO.create(event);

        Event eventDB = eventDAO.findById(event.getId());
        Assert.assertNotNull(eventDB);
        Assert.assertEquals(eventDB, event);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createWithNullObject() {
        eventDAO.create(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createUninitializedEvent() {
        Event event = new Event();
        eventDAO.create(event);
    }

    @Test
    public void findExistingEventById() throws Exception {
        String eventName = "existingEvent";
        List<Event> eventsByName = eventDAO.findByName(eventName);
        Event eventByName = eventsByName.get(0);

        Event eventByID = eventDAO.findById(eventByName.getId());

        Assert.assertNotNull(eventByID);
        Assert.assertEquals(eventByID.getId(), eventByName.getId());
        Assert.assertEquals(eventByID.getName(), eventByName.getName());

    }

    @Test
    public void findNonExistingEventById() throws Exception {
        Long id = Long.MAX_VALUE;
        Assert.assertNull(eventDAO.findById(id));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdWithNull() {
        eventDAO.findById(null);
    }

    @Test
    public void findExistingEventByName() throws Exception {
        String name = "existingEvent";
        List<Event> eventsByName = eventDAO.findByName(name);
        Event eventByName = eventsByName.get(0);
        Assert.assertNotNull(eventByName);
        Assert.assertEquals(name, eventByName.getName());
    }

    @Test
    public void findNonExistingEventByName() throws Exception {
        String name = "nonexisting";
        List<Event> eventsByName = eventDAO.findByName(name);
        Assert.assertNotNull(eventsByName);
        Assert.assertEquals(eventsByName.size(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNameWithNull() {
        eventDAO.findByName(null);
    }

    @Test
    public void findByDate() throws Exception {
        /* All events have as event date set today.
           Only one event has as event date set yesterday.
         */
        List<Event> events = eventDAO.findAll();

        Calendar cal = Calendar.getInstance();

        List<Event> eventsByDate = eventDAO.findByDate(cal);
        Assert.assertNotNull(eventsByDate);
        Assert.assertEquals(eventsByDate.size(), events.size()-1);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        eventsByDate = eventDAO.findByDate(cal);
        Assert.assertNotNull(eventsByDate);
        Assert.assertEquals(eventsByDate.size(), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByDateWithNull() {
        eventDAO.findByDate(null);
    }

    @Test
    public void findBySport() throws Exception {
        Integer events = eventDAO.findAll().size();
        List<Event> fromDB = eventDAO.findBySport(sports.get(0));
        Assert.assertNotNull(fromDB);
        Assert.assertEquals(fromDB.size(), events-1);

        fromDB = eventDAO.findBySport(sports.get(1));
        Assert.assertNotNull(fromDB);
        Assert.assertEquals(fromDB.size(), 1);
        Assert.assertEquals(fromDB.get(0).getName(), "event"+(eventsCount+1));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findBySportWithNull() {
        eventDAO.findBySport(null);
    }

    @Test
    public void findByCity() throws Exception {
        List<Event> events = eventDAO.findAll();
        Event ev = events.get(0);
        String city = "city2";
        ev.setCity(city);
        eventDAO.update(ev);

        List<Event> eventsCity = eventDAO.findByCity("city");
        Assert.assertNotNull(eventsCity);
        Assert.assertEquals(eventsCity.size(), events.size()-1);

        eventsCity = eventDAO.findByCity(city);
        Assert.assertNotNull(eventsCity);
        Assert.assertEquals(eventsCity.size(), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByCityWithNull() {
        eventDAO.findByCity(null);
    }

    @Test
    public void findByAdmin() throws Exception {
        List<Event> fromDB = eventDAO.findByAdmin(sportsmans.get(1));
        Assert.assertNotNull(fromDB);
        Assert.assertEquals(fromDB.size(), eventsCount);//size = 2{event1, event2}
        for (int i = 0; i < fromDB.size(); i++) {
            Assert.assertEquals(fromDB.get(i).getName(), "event"+ (i+1));
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByAdminWithNull() {
        eventDAO.findByAdmin(null);
    }

    @Test
    public void findByParticipant() throws Exception {
        List<Event> eventsByPart = eventDAO.findByParticipant(sportsmans.get(0));
        Assert.assertNotNull(eventsByPart);
        Assert.assertEquals(eventsByPart.size(), 1);

        eventsByPart = eventDAO.findByParticipant(sportsmans.get(1));
        Assert.assertNotNull(eventsByPart);
        Assert.assertEquals(eventsByPart.size(), 1);

        eventsByPart = eventDAO.findByParticipant(sportsmans.get(2));
        Assert.assertNotNull(eventsByPart);
        Assert.assertEquals(eventsByPart.size(), 2);

        eventsByPart = eventDAO.findByParticipant(sportsmans.get(3));
        Assert.assertNotNull(eventsByPart);
        Assert.assertEquals(eventsByPart.size(), 1);

        eventsByPart = eventDAO.findByParticipant(sportsmans.get(4));
        Assert.assertNotNull(eventsByPart);
        Assert.assertEquals(eventsByPart.size(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByParticipantWithNull() {
        eventDAO.findByParticipant(null);
    }

    @Test
    public void findAll() throws Exception {
        List<Event> fromDB = eventDAO.findAll();
        Assert.assertNotNull(fromDB);
        Assert.assertEquals(fromDB.size(), 4);
    }

    @Test
    public void update() throws Exception {
        Event event = getEvent("updateEvent", sportsmans.get(0));
        eventDAO.create(event);
        Long id = event.getId();

        String description = event.getDescription() + "testing desc";
        event.setDescription(description);

        eventDAO.update(event);

        event = eventDAO.findById(id);
        Assert.assertEquals(event.getDescription(), description);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateWithNull() {
        eventDAO.update(null);
    }

    @Test
    public void delete() throws Exception {
        Event event = getEvent("deleteEvent", sportsmans.get(0));
        eventDAO.create(event);
        Long id = event.getId();

        eventDAO.delete(event);
        Assert.assertNull(eventDAO.findById(id));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteWithNull() {
        eventDAO.delete(null);
    }

    private Event getEvent(String name, Sportsman sportsman) {
        return getEvent(name, sportsman, sports.get(0));
    }

    private Event getEvent(String name, Sportsman sportsman, Sport sport) {
        Event event = new Event();
        String eventName = name;
        event.setName(eventName);
        String description = "New event: name";
        event.setDescription(description);
        Calendar date = Calendar.getInstance();
        event.setDate(date);
        event.setAdmin(sportsman);
        Integer capacity = 5;
        event.setCapacity(capacity);
        event.setSport(sport);
        String address = "address";
        event.setAddress(address);
        String city = "city";
        event.setCity(city);
        return event;
    }

    private List<Sportsman> generateSportmans(int count) {
        List<Sportsman> sportsmanList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Sportsman sportsman = new Sportsman();
            sportsman.setName("Sportsman" + i);
            sportsman.setSurname("Sportsman" + i);
            sportsman.setBirthDate(Calendar.getInstance());
            sportsman.setEmail("sportsman"+i+"@email.com");
            sportsman.setPassword("pass1");
            sportsman.setIsManager(true);
            sportsmanDAO.create(sportsman);
            sportsmanList.add(sportsman);
        }
        return sportsmanList;
    }

    public List<Sport> generateSports(int count) {
        List<Sport> sportsList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Sport sport = new Sport();
            sport.setName("Sport"+i);
            sport.setDescription("Sport" + i + " description");
            sportDAO.create(sport);
            sportsList.add(sport);
        }
        return sportsList;
    }

}