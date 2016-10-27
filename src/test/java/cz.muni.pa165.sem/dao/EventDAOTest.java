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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * @author Kamil Triscik
 */
@Transactional
@ContextConfiguration(locations = "classpath:dao-context.xml")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class EventDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private SportsmanDAO sportsmanDAO;

    @Autowired
    private SportDAO sportDAO;

    @BeforeClass
    public void setUp() throws Exception {   }

//    @After
//    public void tearDown() throws Exception {
//
//    }

    @Test
    public void createValidData() throws Exception {
        Sportsman sportsman = new Sportsman();
        sportsman.setName("Sportsman1");
        sportsman.setSurname("Sportsman1");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail("sportsman1@email.com");
        sportsman.setPassword("pass1");
        sportsmanDAO.create(sportsman);

        Sport sport = new Sport();
        sport.setName("Marathon");
        sport.setDescription("Marathon description");
        sportDAO.create(sport);


        Event event = new Event();
        String eventName = "newEvent";
        event.setName(eventName);
        String description = "New event description";
        event.setDescription(description);
        Calendar date = Calendar.getInstance();
        event.setDate(date);
        event.setAdmin(sportsman);
        Integer capacity = 5;
        event.setCapacity(capacity);
        event.setSport(sport);
        String address = "Botanicka 35";
        event.setAddress(address);
        String city = "Brno";
        event.setCity(city);
        eventDAO.create(event);

        Event eventDB = eventDAO.findById(event.getId());
        Assert.assertNotNull(eventDB);
        Assert.assertEquals(eventDB.getName(), eventName);
        Assert.assertEquals(eventDB.getDescription(), description);
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



//    @Test
//    public void findById() throws Exception {
//
//    }
//
//    @Test
//    public void findByName() throws Exception {
//
//    }
//
//    @Test
//    public void findByDate() throws Exception {
//
//    }
//
//    @Test
//    public void findBySport() throws Exception {
//
//    }
//
//    @Test
//    public void findByCity() throws Exception {
//
//    }
//
//    public void findByAdmin() throws Exception {
//
//    }
//
//    @Test
//    public void findByParticipant() throws Exception {
//
//    }
//
//    @Test
//    public void findAll() throws Exception {
//
//    }
//
//    @Test
//    public void update() throws Exception {
//
//    }
//
//    @Test
//    public void delete() throws Exception {
//
//    }

}