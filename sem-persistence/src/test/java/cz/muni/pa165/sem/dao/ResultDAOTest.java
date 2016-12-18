package cz.muni.pa165.sem.dao;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.utils.PerformanceUnits;
import cz.muni.pa165.sem.entity.Result;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Vit Hovezak
 */
@Transactional
@ContextConfiguration(locations = "classpath:dao-context.xml")
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class ResultDAOTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private EventDAO eventDAO;
    
    @Autowired
    private ResultDAO resultDAO;
    
    @Autowired
    private SportDAO sportDAO;
    
    @Autowired
    private SportsmanDAO sportsmanDAO;
    
    private List<Event> events;
    
    private List<Sport> sports;
    
    private List<Sportsman> sportsmans;
    
    private final Double resultPerformance = new Double("9.58");
    
    private final String resultNote = "World record";
    
    @BeforeMethod
    public void setUp() {
        sports = generateSports(10);
        sportsmans = generateSportsmans(10);
        events = generateEvents(10);
        
        Result result = getResult(resultPerformance, resultNote);
        resultDAO.create(result);
    }
    
    @AfterMethod
    public void tearDown() {
        List<Result> results = resultDAO.findAll();
        results.forEach(resultDAO::delete);
        List<Event> events = eventDAO.findAll();
        events.forEach(eventDAO::delete);
        List<Sport> sports = sportDAO.findAll();
        sports.forEach(sportDAO::delete);
        List<Sportsman> sportsmans = sportsmanDAO.findAll();
        sportsmans.forEach(sportsmanDAO::delete);
    }

    /**
     * Test of create method, of class ResultDAO.
     */
    @Test
    public void testCreate() {
        Result expResult = getResult(new Double("12.34"), "Valid attempt");
        resultDAO.create(expResult);

        Result result = resultDAO.findById(expResult.getId());
        assertNotNull(result);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of create method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
        resultDAO.create(null);
    }

    /**
     * Test of findById method, of class ResultDAO.
     */
    @Test
    public void testFindById() {
        List<Result> results = resultDAO.findByNote(resultNote);
        Result expResult = results.get(0);
        
        Result result = resultDAO.findById(expResult.getId());
        assertNotNull(result);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of findById method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNull() {
        resultDAO.findById(null);
    }

    /**
     * Test of findById method with not found, of class ResultDAO.
     */
    @Test
    public void testFindByIdNotFound() {
        Result result = resultDAO.findById(new Long("123"));
        assertNull(result);
    }

    /**
     * Test of findBySportsman method, of class ResultDAO.
     */
    @Test
    public void testFindBySportsman() {
        Sportsman sportsman = sportsmans.get(5);
        Result expResult = getResult(new Double("12.34"), sportsman, "Valid attempt");
        resultDAO.create(expResult);

        List<Result> results = resultDAO.findBySportsman(sportsman);
        Result result = results.get(0);
        assertNotNull(result);
        assertEquals(sportsman, result.getSportsman());
    }
    
    /**
     * Test of findBySportsman method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindBySportsmanNull() {
        resultDAO.findBySportsman(null);
    }

    /**
     * Test of findBySportsman method with not found, of class ResultDAO.
     */
    @Test
    public void testFindBySportsmanNotFound() {
        List<Result> results = resultDAO.findBySportsman(sportsmans.get(9));
        assertEquals(true, results.isEmpty());
    }

    /**
     * Test of findByEvent method, of class ResultDAO.
     */
    @Test
    public void testFindByEvent() {
        Event event = events.get(1);
        Result expResult = getResult(new Double("12.34"), "Valid attempt");
        resultDAO.create(expResult);

        List<Result> results = resultDAO.findByEvent(event);
        Result result = results.get(0);
        assertNotNull(result);
        assertEquals(event, result.getEvent());
    }
    
    /**
     * Test of findByEvent method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByEventNull() {
        resultDAO.findByEvent(null);
    }

    /**
     * Test of findByEvent method with not found, of class ResultDAO.
     */
    @Test
    public void testFindByEventNotFound() {
        List<Result> results = resultDAO.findByEvent(events.get(9));
        assertEquals(true, results.isEmpty());
    }

    /**
     * Test of findBySport method, of class ResultDAO.
     */
    @Test
    public void testFindBySport() {
        Sport sport = sports.get(1);
        Result expResult = getResult(new Double("12.34"), "Valid attempt");
        resultDAO.create(expResult);

        List<Result> results = resultDAO.findBySport(sport);
        Result result = results.get(0);
        assertNotNull(result);
        assertEquals(sport, result.getEvent().getSport());
    }
    
    /**
     * Test of findBySport method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindBySportNull() {
        resultDAO.findBySport(null);
    }

    /**
     * Test of findBySport method with not found, of class ResultDAO.
     */
    @Test
    public void testFindBySportNotFound() {
        List<Result> results = resultDAO.findBySport(sports.get(9));
        assertEquals(true, results.isEmpty());
    }

    /**
     * Test of findByPerformance method, of class ResultDAO.
     */
    @Test
    public void testFindByPerformance() {
        final Double performance = new Double("12.34");
        
        Result expResult = getResult(performance, "Valid attempt");
        resultDAO.create(expResult);

        List<Result> results = resultDAO.findByPerformance(performance);
        Result result = results.get(0);
        assertNotNull(result);
        assertEquals(performance, result.getPerformance());
        
        results = resultDAO.findByPerformance(resultPerformance);
        result = results.get(0);
        assertNotNull(result);
        assertEquals(resultPerformance, result.getPerformance());
    }
    
    /**
     * Test of findByPerformance method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByPerformanceNull() {
        resultDAO.findByPerformance(null);
    }

    /**
     * Test of findByPerformance method with not found, of class ResultDAO.
     */
    @Test
    public void testFindByPerformanceNotFound() {
        List<Result> results = resultDAO.findByPerformance(new Double("123"));
        assertEquals(true, results.isEmpty());
    }

    /**
     * Test of findByPosition method, of class ResultDAO.
     */
    @Test
    public void testFindByPosition() {
        final Integer position = new Integer("1");
        
        Result expResult = getResult(new Double("12.34"), "Valid attempt");
        resultDAO.create(expResult);

        List<Result> results = resultDAO.findByPosition(position);
        Result result = results.get(0);
        assertNotNull(result);
        assertEquals(position, result.getPosition());
    }
    
    /**
     * Test of findByPosition method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByPositionNull() {
        resultDAO.findByPosition(null);
    }

    /**
     * Test of findByPosition method with not found, of class ResultDAO.
     */
    @Test
    public void testFindByPositionNotFound() {
        List<Result> results = resultDAO.findByPosition(new Integer("123"));
        assertEquals(true, results.isEmpty());
    }

    /**
     * Test of findByNote method, of class ResultDAO.
     */
    @Test
    public void testFindByNote() {
        final String note = "Valid attempt";
        
        Result expResult = getResult(new Double("12.34"), note);
        resultDAO.create(expResult);

        List<Result> results = resultDAO.findByNote(note);
        Result result = results.get(0);
        assertNotNull(result);
        assertEquals(note, result.getNote());
        
        results = resultDAO.findByNote(resultNote);
        result = results.get(0);
        assertNotNull(result);
        assertEquals(resultNote, result.getNote());
    }
    
    /**
     * Test of findByNote method with null, of class ResultDAO.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNoteNull() {
        resultDAO.findByNote(null);
    }

    /**
     * Test of findByNote method with not found, of class ResultDAO.
     */
    @Test
    public void testFindByNoteNotFound() {
        List<Result> results = resultDAO.findByNote("Not existing note");
        assertEquals(true, results.isEmpty());
    }

    /**
     * Test of findAll method, of class ResultDAO.
     */
    @Test
    public void testFindAll() {
        List<Result> results = resultDAO.findAll();
        assertNotNull(results);
    }

    /**
     * Test of update method, of class ResultDAO.
     */
    @Test
    public void testUpdate() {
        final String updatedNote = "Updated note";
        
        Result expResult = getResult(new Double("12.34"), "Valid attempt");
        resultDAO.create(expResult);
        expResult.setNote(updatedNote);
        resultDAO.update(expResult);

        Result result = resultDAO.findById(expResult.getId());
        assertNotNull(result);
        assertEquals(updatedNote, result.getNote());
    }

    /**
     * Test of delete method, of class ResultDAO.
     */
    @Test
    public void testDelete() {
        Result expResult = getResult(new Double("12.34"), "Valid attempt");
        resultDAO.create(expResult);
        resultDAO.delete(expResult);
        
        Result result = resultDAO.findById(expResult.getId());
        assertNull(result);
    }
    
    public List<Event> generateEvents(int count) {
        List<Event> events = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            int evenOrOdd = (i % 2 == 0) ? 1 : 0;
            Event event = new Event();
            event.setName("EventName" + i);
            event.setDescription("EventDescription" + i);
            event.setDate(Calendar.getInstance());
            event.setSport(sports.get(evenOrOdd));
            event.setCapacity(10 + i);
            event.setCity("EventCity" + i);
            event.setAddress("EventAddress" + i);
            event.setAdmin(sportsmans.get(evenOrOdd));
            eventDAO.create(event);
            events.add(event);
        }
        return events;
    }
    
    public List<Sport> generateSports(int count) {
        List<Sport> sports = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Sport sport = new Sport();
            sport.setName("SportName" + i);
            sport.setDescription("SportDescription" + i);
            sportDAO.create(sport);
            sports.add(sport);
        }
        return sports;
    }

    public List<Sportsman> generateSportsmans(int count) {
        List<Sportsman> sportsmans = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Sportsman sportsman = new Sportsman();
            sportsman.setName("SportsmanName" + i);
            sportsman.setSurname("SportsmanSurname" + i);
            sportsman.setBirthDate(Calendar.getInstance());
            sportsman.setEmail("sportsman" + i + "@sem.muni.cz");
            sportsman.setPassword("SportsmanPassword" + i);
            sportsman.setIsManager(true);
            sportsmanDAO.create(sportsman);
            sportsmans.add(sportsman);
        }
        return sportsmans;
    }
    
    public Result getResult(Double performance, String note) {
        Sportsman sportsman = sportsmans.get(0);
        return getResult(performance, sportsman, note);
    }
    
    public Result getResult(Double performance, Sportsman sportsman, String note) {
        Result result = new Result();
        result.setPerformance(performance);
        result.setPerformanceUnit(PerformanceUnits.SECOND);
        result.setPosition(new Integer("1"));
        result.setSportsman(sportsman);
        result.setNote(note);
        result.setEvent(events.get(1));
        return result;
    }
    
}
