package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportDAO;
import cz.muni.pa165.sem.entity.Sport;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;
import static org.mockito.Matchers.argThat;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

/**
 * @author Kamil Triscik
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class SportServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private SportService sportService = new SportServiceImpl();

    @Mock
    private SportDAO sportDAO;

    private Sport sport;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sport = new Sport();
        sport.setName("sport1");
        sport.setDescription("sport1 desc");

        Mockito.when(sportDAO.findByName("sport1")).thenReturn(sport);
        Mockito.when(sportDAO.findByName(argThat(not("sport1")))).thenReturn(null);
        Mockito.when(sportDAO.findById(1L)).thenReturn(sport);
        Mockito.when(sportDAO.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(sportDAO.findAll()).thenReturn(Collections.singletonList(sport));
    }

    @Test
    public void testFindExistingById() {
        Long id = 1L;
        Sport sportDB = sportService.findById(id);
        Assert.assertNotNull(sportDB);
        Mockito.verify(sportDAO, times(1)).findById(id);
    }

    @Test
    public void testFindNonExistingById() {
        Long id = 2L;
        Sport sportDB = sportService.findById(id);
        Assert.assertNull(sportDB);
        Mockito.verify(sportDAO, times(1)).findById(id);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(sportDAO)
                .findById(null);

        sportService.findById(null);
    }

    @Test
    public void testFindExistingByName() {
        String name  = "sport1";
        Sport sportDB = sportService.findByName(name);
        Assert.assertNotNull(sportDB);
        Mockito.verify(sportDAO, times(1)).findByName(name);

    }

    @Test
    public void testFindNonExistingByName() {
        String name  = "sport2";
        Sport sportDB = sportService.findByName(name);
        Assert.assertNull(sportDB);
        Mockito.verify(sportDAO, times(1)).findByName(name);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullName() {
        doThrow(new IllegalArgumentException("Trying to find object by null name!"))
                .when(sportDAO)
                .findByName(null);

        sportService.findByName(null);
    }

    @Test
    public void testFindAll() {
        List<Sport> sportsDB = sportService.findAll();

        Mockito.verify(sportDAO, times(1)).findAll();

        Assert.assertNotNull(sportsDB);
        Assert.assertEquals(sportsDB.size(), 1);
    }

    @Test
    public void testCreate() {
        sportService.create(sport);

        Mockito.verify(sportDAO, times(1)).create(sport);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
        doThrow(new IllegalArgumentException("Trying to createEvent null object!"))
                .when(sportDAO)
                .create(null);

        sportService.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUninitializedObject() {
        Sport sport= new Sport();
        doThrow(new IllegalArgumentException("Trying to createEvent uninitialized object!"))
                .when(sportDAO)
                .create(sport);

        sportService.create(sport);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAlreadyExisted() {
        doThrow(new IllegalArgumentException("Trying to createEvent already existing object!"))
                .when(sportDAO)
                .create(this.sport);

        sportService.create(sport);
    }

    @Test
    public void testUpdate() {
        Sport updateSport = sportService.findById(1L);
        updateSport.setDescription("new sport1 desc");

        sportService.update(updateSport);

        Mockito.verify(sportDAO, times(1)).update(updateSport);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull() {
        doThrow(new IllegalArgumentException("Trying to updateEvent null object!"))
                .when(sportDAO)
                .update(null);

        sportService.update(null);
    }

    @Test
    public void testDelete() {
        Sport deleteSport = sportService.findById(1L);
        Assert.assertNotNull(deleteSport);

        sportService.delete(deleteSport);

        Mockito.verify(sportDAO, times(1)).delete(deleteSport);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        doThrow(new IllegalArgumentException("Trying to deleteEvent null object!"))
                .when(sportDAO)
                .delete(null);

        sportService.delete(null);
    }

}
