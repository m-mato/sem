package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.*;
import cz.muni.pa165.sem.entity.*;

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
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class SportsmanServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private SportsmanService sportsmanService = new SportsmanServiceImpl();

    @Mock
    private SportsmanDAO sportsmanDAO;

    private Sportsman sportsman;
    private List sportsmen;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sportsmen = new ArrayList();
        sportsman = new Sportsman();
        sportsman.setName("Sportsman");
        sportsman.setSurname("Surname");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail("sportsman@email.com");
        sportsman.setPassword("pass");
        sportsmen.add(sportsman);
        sportsman.setEvents(new HashSet<>());
        sportsman.setInvitations(new HashSet<>());

        Mockito.when(sportsmanDAO.findByName("Sportsman")).thenReturn(Collections.singletonList(sportsman));
        Mockito.when(sportsmanDAO.findByName(argThat(not("Sportsman")))).thenReturn(new ArrayList<>());
        Mockito.when(sportsmanDAO.findBySurname("Surname")).thenReturn(sportsmen);
        Mockito.when(sportsmanDAO.findBySurname(argThat(not("Surname")))).thenReturn(new ArrayList<>());
        Mockito.when(sportsmanDAO.findById(1L)).thenReturn(sportsman);
        Mockito.when(sportsmanDAO.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(sportsmanDAO.findAll()).thenReturn(sportsmen);
    }

    @Test
    public void testCreate() {
        sportsmanService.create(sportsman);
        Mockito.verify(sportsmanDAO, times(1)).create(sportsman);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull() {
        Sportsman create = null;
        doThrow(new IllegalArgumentException("Trying to createEvent null object!"))
                .when(sportsmanDAO)
                .create(create);

        sportsmanService.create(create);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUninitializedObject() {
        Sportsman sportsman = new Sportsman();
        doThrow(new IllegalArgumentException("Trying to createEvent uninitialized object!"))
                .when(sportsmanDAO)
                .create(sportsman);

        sportsmanService.create(sportsman);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAlreadyExisted() {
        doThrow(new IllegalArgumentException("Trying to createEvent already existing object!"))
                .when(sportsmanDAO)
                .create(this.sportsman);

        sportsmanService.create(sportsman);
    }

    @Test
    public void testFindExistingById() {
        Long id = 1L;
        Sportsman sportsmanDB = sportsmanService.findById(id);
        Assert.assertNotNull(sportsmanDB);
        Mockito.verify(sportsmanDAO, times(1)).findById(id);
    }

    @Test
    public void testFindNonExistingById() {
        Long id = 4L;
        Sportsman sportsmanDB = sportsmanService.findById(id);
        Assert.assertNull(sportsmanDB);
        Mockito.verify(sportsmanDAO, times(1)).findById(id);
    }

    @Test
    public void testFindByExistingName() {
        String name = "Sportsman";
        List<Sportsman> sportsmansDB = sportsmanService.findByName(name);
        Assert.assertNotNull(sportsmansDB);
        Assert.assertEquals(sportsmansDB.size(),1);
        Mockito.verify(sportsmanDAO, times(1)).findByName(name);
    }

    @Test
    public void testFindByNonExistingName() {
        String name = "Sportsman1";
        List<Sportsman> sportsmansDB = sportsmanService.findByName(name);
        Assert.assertNotNull(sportsmansDB);
        Assert.assertTrue(sportsmansDB.isEmpty());
        Mockito.verify(sportsmanDAO, times(1)).findByName(name);
    }

    @Test
    public void testFindByExistingSurname() {
        String surname = "Surname";
        List<Sportsman> sportsmansDB = sportsmanService.findBySurname(surname);
        Assert.assertNotNull(sportsmansDB);
        Assert.assertEquals(sportsmansDB.size(),sportsmen.size());
        Mockito.verify(sportsmanDAO, times(1)).findBySurname(surname);
    }

    @Test
    public void testFindByNonExistingSurname() {
        String surname = "Surname+1";
        List<Sportsman> sportsmansDB = sportsmanService.findBySurname(surname);
        Assert.assertNotNull(sportsmansDB);
        Assert.assertTrue(sportsmansDB.isEmpty());
        Mockito.verify(sportsmanDAO, times(1)).findBySurname(surname);
    }

    @Test
    public void testFindAll() {
        List<Sportsman> sportsmansDB = sportsmanService.findAll();
        Assert.assertNotNull(sportsmansDB);
        Assert.assertEquals(sportsmansDB.size(),sportsmen.size());
        Mockito.verify(sportsmanDAO, times(1)).findAll();
    }

    @Test
    public void testUpdate() {
        sportsmanService.update(sportsman);
        Mockito.verify(sportsmanDAO, times(1)).update(sportsman);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull() {
        doThrow(new IllegalArgumentException("Trying to updateEvent null object!"))
                .when(sportsmanDAO)
                .update(null);

        sportsmanService.update(null);
    }

    @Test
    public void testDelete() {
        sportsmanService.delete(sportsman);
        Mockito.verify(sportsmanDAO, times(1)).delete(sportsman);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull() {
        doThrow(new IllegalArgumentException("Trying to deleteEvent null object!"))
                .when(sportsmanDAO)
                .delete(null);

        sportsmanService.delete(null);
    }
}
