package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.SportDAO;
import cz.muni.pa165.sem.entity.Sport;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.argThat;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.times;

/**
 * @author Kamil Triscik
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class SportServiceTest {

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
        //Mockito.doThrow(new DataAccessException("")).when()
        Mockito.when(sportDAO.findAll()).thenReturn(Collections.singletonList(sport));
    }

    @Test
    public void findExistingById() {
        Sport sportDB = sportService.findById(1L);
        Assert.assertNotNull(sportDB);
        Assert.assertEquals(sportDB.getName(), this.sport.getName());
        Assert.assertEquals(sportDB.getDescription(), this.sport.getDescription());
    }

    @Test
    public void findNonExistingById() {
        Sport sportDB = sportService.findById(2L);
        Assert.assertNull(sportDB);
    }

    @Test
    public void findExistingByName() {
        Sport sportDB = sportService.findByName("sport1");
        Assert.assertNotNull(sportDB);
        Assert.assertEquals(sportDB.getName(), this.sport.getName());
        Assert.assertEquals(sportDB.getDescription(), this.sport.getDescription());
    }

    @Test
    public void findNonExistingByName() {
        Sport sportDB = sportService.findByName("sport2");
        Assert.assertNull(sportDB);
    }

    @Test
    public void create() {
        Sport newSport = new Sport();
        newSport.setName("newSport");
        newSport.setDescription("newSport desc");
        sportService.create(newSport);

        Mockito.verify(sportDAO, times(1)).create(newSport);
    }

    @Test
    public void createAlreadyExisted() {
        Sport newSport = new Sport();
        newSport.setName("sport1");
        newSport.setDescription("newSport desc");
        sportService.create(newSport);

        //Mockito.verify(sportDAO, times(1)).create(newSport);
    }

    @Test
    public void update() {
        Sport updateSport = sportService.findById(1L);
        updateSport.setDescription("new sport1 desc");

        sportService.update(updateSport);

        Mockito.verify(sportDAO, times(1)).update(updateSport);
    }

    @Test
    public void delete() {
        Sport deleteSport = sportService.findById(1L);
        Assert.assertNotNull(deleteSport);

        sportService.delete(deleteSport);

        Mockito.verify(sportDAO, times(1)).delete(deleteSport);
    }

    @Test
    public void findAll() {
        List<Sport> sportsDB = sportService.findAll();

        Mockito.verify(sportDAO, times(1)).findAll();

        Assert.assertNotNull(sportsDB);
        Assert.assertEquals(sportsDB.size(), 1);
    }

}
