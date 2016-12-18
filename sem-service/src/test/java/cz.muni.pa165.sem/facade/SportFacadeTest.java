package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.SportCreateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportUpdateDTO;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.service.SportService;
import cz.muni.pa165.sem.service.facade.SportFacadeImpl;

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
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Matchers.argThat;

/**
 * @author Vit Hovezak
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class SportFacadeTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private SportFacadeImpl sportFacade = new SportFacadeImpl();

    @Mock
    private SportService sportService;

    @Autowired
    private BeanMappingService beanMappingService;

    private Sport sport;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sportFacade.setBeanMappingService(beanMappingService);

        sport = new Sport();
        sport.setName("Sport 1 name");
        sport.setDescription("Sport 1 description");

        Mockito.when(sportService.findById(1L)).thenReturn(sport);
        Mockito.when(sportService.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(sportService.findByName("Sport 1 name")).thenReturn(sport);
        Mockito.when(sportService.findByName(argThat(not("Sport 1 name")))).thenReturn(null);
        Mockito.when(sportService.findAll()).thenReturn(Collections.singletonList(sport));
    }

    @Test
    public void create() {
        SportCreateDTO sportCreateDTO = new SportCreateDTO();
        sportCreateDTO.setName("Sport 2 name");
        sportCreateDTO.setDescription("Sport 2 description");

        sportFacade.create(sportCreateDTO);
        Sport createdSport = beanMappingService.mapTo(sportCreateDTO, Sport.class);

        Mockito.verify(sportService).create(createdSport);
    }

    @Test
    public void getExistingSportById() {
        SportDTO sportDTO = sportFacade.getSportById(1L);
        Sport foundSport = beanMappingService.mapTo(sportDTO, Sport.class);

        Mockito.verify(sportService).findById(1L);
        Assert.assertEquals(foundSport, sport);
    }

    @Test
    public void getNonExistingSportById() {
        SportDTO sportDTO = sportFacade.getSportById(2L);

        Mockito.verify(sportService).findById(2L);
        Assert.assertNull(sportDTO);
    }

    @Test
    public void getExistingSportByName() {
        SportDTO sportDTO = sportFacade.getSportByName("Sport 1 name");
        Sport foundSport = beanMappingService.mapTo(sportDTO, Sport.class);

        Mockito.verify(sportService).findByName("Sport 1 name");
        Assert.assertEquals(foundSport, sport);
    }

    @Test
    public void getNonExistingSportByName() {
        SportDTO sportDTO = sportFacade.getSportByName("Sport 2 name");

        Mockito.verify(sportService).findByName("Sport 2 name");
        Assert.assertNull(sportDTO);
    }

    @Test
    public void getAllSports() {
        List<SportDTO> sportDTOs = sportFacade.getAllSports();

        Mockito.verify(sportService).findAll();
        Assert.assertNotNull(sportDTOs);
        Assert.assertEquals(sportDTOs.size(), 1);
    }

    @Test
    public void update() {
        SportUpdateDTO sportUpdateDTO = beanMappingService.mapTo(sport, SportUpdateDTO.class);
        sportUpdateDTO.setId(1L);

        sportFacade.update(sportUpdateDTO);

        Mockito.verify(sportService).update(sport);
    }

    @Test
    public void delete() {
        SportDTO sportDTO = sportFacade.getSportById(1L);
        sportDTO.setId(1L);

        sportFacade.delete(sportDTO.getId());

        Mockito.verify(sportService).delete(sport);
    }

}
