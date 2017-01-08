package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.*;
import cz.muni.pa165.sem.entity.*;
import cz.muni.pa165.sem.utils.PerformanceUnits;

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
public class ResultServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private  ResultService resultService = new ResultServiceImpl();

    @Mock
    private ResultDAO resultDAO;

    private Result result;
    private Event event;
    private Sport sport;
    private Sportsman sportsman;

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
        Calendar date = Calendar.getInstance();
        event.setDate(date);
        event.setAdmin(sportsman);
        Integer capacity = 5;
        event.setCapacity(capacity);
        event.setSport(sport);
        String address = "Address";
        event.setAddress(address);
        String city = "city";
        event.setCity(city);


        result = new Result();
        result.setNote("note");
        result.setPosition(2);
        result.setPerformance(9.2);
        result.setPerformanceUnit(PerformanceUnits.SECOND);
        result.setSportsman(sportsman);
        result.setEvent(event);

        Mockito.when(resultDAO.findById(1L)).thenReturn(result);
        Mockito.when(resultDAO.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(resultDAO.findBySportsman(sportsman)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultDAO.findBySportsman(argThat(not(sportsman)))).thenReturn(new ArrayList<>());
        Mockito.when(resultDAO.findByEvent(event)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultDAO.findByEvent(argThat(not(event)))).thenReturn(new ArrayList<>());
        Mockito.when(resultDAO.findBySport(sport)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultDAO.findBySport(argThat(not(sport)))).thenReturn(new ArrayList<>());
        Mockito.when(resultDAO.findByPerformance(9.2)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultDAO.findByPerformance(argThat(not(9.2)))).thenReturn(new ArrayList<>());
        Mockito.when(resultDAO.findByPosition(2)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultDAO.findByPosition(argThat(not(2)))).thenReturn(new ArrayList<>());
        Mockito.when(resultDAO.findByNote("note")).thenReturn(Collections.singletonList(result));
        Mockito.when(resultDAO.findByNote(argThat(not("note")))).thenReturn(new ArrayList<>());
        Mockito.when(resultDAO.findAll()).thenReturn(Collections.singletonList(result));
    }

    @Test
    public void testCreate() {
        resultService.create(result);

        Mockito.verify(resultDAO, times(1)).create(result);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
        doThrow(new IllegalArgumentException("Trying to createEvent null object!"))
                .when(resultDAO)
                .create(null);

        resultService.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUninitializedObject() {
        Result result1 = new Result();
        doThrow(new IllegalArgumentException("Trying to createEvent uninitialized object!"))
                .when(resultDAO)
                .create(result1);

        resultService.create(result1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAlreadyExisted() {
        doThrow(new IllegalArgumentException("Trying to createEvent already existing object!"))
                .when(resultDAO)
                .create(this.result);

        resultService.create(result);
    }

    @Test
    public void testUpdate() {
        resultService.update(result);
        Mockito.verify(resultDAO, times(1)).update(result);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull() {
        doThrow(new IllegalArgumentException("Trying to updateEvent null object!"))
                .when(resultDAO)
                .update(null);

        resultService.update(null);
    }

    @Test
    public void testDelete() {
        resultService.delete(result);
        Mockito.verify(resultDAO, times(1)).delete(result);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNotExistingResult() {
        Result res = new Result();
        res.setPosition(3);
        doThrow(new IllegalArgumentException("Trying to deleteEvent not existing object!"))
                .when(resultDAO)
                .delete(res);

        resultService.delete(res);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        doThrow(new IllegalArgumentException("Trying to deleteEvent null object!"))
                .when(resultDAO)
                .delete(null);

        resultService.delete(null);
    }

    @Test
    public void testFindExistingById() {
        Long id = 1L;
        Result result = resultService.findById(id);
        Assert.assertNotNull(result);
        Mockito.verify(resultDAO, times(1)).findById(id);
    }

    @Test
    public void testFindNonExistingById() {
        Long id = 2L;
        Result result1 = resultService.findById(id);
        Assert.assertNull(result1);
        Mockito.verify(resultDAO, times(1)).findById(id);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(resultDAO)
                .findById(null);

        resultService.findById(null);
    }

    @Test
    public void testFindExistingBySportsman() {
        List<Result> results = resultService.findBySportsman(sportsman);
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(),1);
        Mockito.verify(resultDAO, times(1)).findBySportsman(sportsman);
    }

    @Test
    public void testFindNonExistingBySportsman() {
        Sportsman sportsman1 = new Sportsman();
        List<Result> results = resultService.findBySportsman(sportsman1);
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
        Mockito.verify(resultDAO, times(1)).findBySportsman(sportsman1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullSportsman() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(resultDAO)
                .findBySportsman(null);

        resultService.findBySportsman(null);
    }

    @Test
    public void testFindExistingByEvent() {
        List<Result> results = resultService.findByEvent(event);
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(),1);
        Mockito.verify(resultDAO, times(1)).findByEvent(event);
    }

    @Test
    public void testFindNonExistingByEvent() {
        Event event1 = new Event();
        List<Result> results = resultService.findByEvent(event1);
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
        Mockito.verify(resultDAO, times(1)).findByEvent(event1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullEvent() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(resultDAO)
                .findByEvent(null);

        resultService.findByEvent(null);
    }

    @Test
    public void testFindExistingBySport() {
        List<Result> results = resultService.findBySport(sport);
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(),1);
        Mockito.verify(resultDAO, times(1)).findBySport(sport);
    }

    @Test
    public void testFindNonExistingBySport() {
        Sport sport1 = new Sport();
        List<Result> results = resultService.findBySport(sport1);
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
        Mockito.verify(resultDAO, times(1)).findBySport(sport1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullSport() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(resultDAO)
                .findBySport(null);

        resultService.findBySport(null);
    }

    @Test
    public void testFindExistingByPerformance() {
        Double performance = 9.2;
        List<Result> results = resultService.findByPerformance(performance);
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(),1);
        Mockito.verify(resultDAO, times(1)).findByPerformance(performance);
    }

    @Test
    public void testFindNonExistingByPerformance() {
        Double performance = 15.2;
        List<Result> results = resultService.findByPerformance(performance);
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
        Mockito.verify(resultDAO, times(1)).findByPerformance(performance);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullPerformance() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(resultDAO)
                .findByPerformance(null);

        resultService.findByPerformance(null);
    }

    @Test
    public void testFindExistingByPosition() {
        Integer position = 2;
        List<Result> results = resultService.findByPosition(position);
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(),1);
        Mockito.verify(resultDAO, times(1)).findByPosition(position);
    }

    @Test
    public void testFindNonExistingByPosition() {
        Integer position = 3;
        List<Result> results = resultService.findByPosition(position);
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
        Mockito.verify(resultDAO, times(1)).findByPosition(position);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullPosition() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(resultDAO)
                .findByPosition(null);

        resultService.findByPosition(null);
    }

    @Test
    public void testFindExistingByNote() {
        String note = "note";
        List<Result> results = resultService.findByNote(note);
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(),1);
        Mockito.verify(resultDAO, times(1)).findByNote(note);
    }

    @Test
    public void testFindNonExistingByNote() {
        String note = "note1";
        List<Result> results = resultService.findByNote(note);
        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
        Mockito.verify(resultDAO, times(1)).findByNote(note);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullNote() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(resultDAO)
                .findByNote(null);

        resultService.findByNote(null);
    }

    @Test
    public void testFindAll() {
        List<Result> results = resultService.findAll();
        Assert.assertNotNull(results);
        Assert.assertEquals(results.size(),1);
        Mockito.verify(resultDAO, times(1)).findAll();
    }


}
