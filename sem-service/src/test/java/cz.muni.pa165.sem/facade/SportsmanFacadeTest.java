package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.SportsmanCreateDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.dto.SportsmanUpdateDTO;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.service.SportsmanService;
import cz.muni.pa165.sem.service.facade.SportsmanFacadeImpl;

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
public class SportsmanFacadeTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private SportsmanFacadeImpl sportsmanFacade = new SportsmanFacadeImpl();

    @Mock
    private SportsmanService sportsmanService;

    @Autowired
    private BeanMappingService beanMappingService;

    private Sportsman sportsman;
    private Calendar date;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sportsmanFacade.setBeanMappingService(beanMappingService);

        date = Calendar.getInstance();

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
        Mockito.when(sportsmanService.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(sportsmanService.findByName("Sportsman 1 name")).thenReturn(Collections.singletonList(sportsman));
        Mockito.when(sportsmanService.findByName(argThat(not("Sportsman 1 name")))).thenReturn(Collections.emptyList());
        Mockito.when(sportsmanService.findBySurname("Sportsman 1 surname")).thenReturn(Collections.singletonList(sportsman));
        Mockito.when(sportsmanService.findBySurname(argThat(not("Sportsman 1 surname")))).thenReturn(Collections.emptyList());
        Mockito.when(sportsmanService.findAll()).thenReturn(Collections.singletonList(sportsman));
    }

    @Test
    public void testCreate() {
        SportsmanCreateDTO sportsmanCreateDTO = new SportsmanCreateDTO();
        sportsmanCreateDTO.setName("Sportsman 1 name");
        sportsmanCreateDTO.setSurname("Sportsman 1 surname");
        sportsmanCreateDTO.setBirthDate(date);
        sportsmanCreateDTO.setEmail("Sportsman 1 email");
        sportsmanCreateDTO.setPassword("Sportsman 1 password");

        sportsmanFacade.create(sportsmanCreateDTO);
        Sportsman createdSportsman = beanMappingService.mapTo(sportsmanCreateDTO, Sportsman.class);

        Mockito.verify(sportsmanService).create(createdSportsman);
    }

    @Test
    public void testGetExistingById() {
        SportsmanDTO sportsmanDTO = sportsmanFacade.getById(1L);
        Sportsman foundSportsman = beanMappingService.mapTo(sportsmanDTO, Sportsman.class);

        Mockito.verify(sportsmanService).findById(1L);
        Assert.assertEquals(foundSportsman, sportsman);
    }

    @Test
    public void testGetNonExistingById() {
        SportsmanDTO sportsmanDTO = sportsmanFacade.getById(2L);

        Mockito.verify(sportsmanService).findById(2L);
        Assert.assertNull(sportsmanDTO);
    }

    @Test
    public void testGetByName() {
        List<SportsmanDTO> sportsmanDTOs = sportsmanFacade.getByName("Sportsman 1 name");
        Sportsman foundSportsman = beanMappingService.mapTo(sportsmanDTOs.get(0), Sportsman.class);

        Mockito.verify(sportsmanService).findByName("Sportsman 1 name");
        Assert.assertNotNull(sportsmanDTOs);
        Assert.assertEquals(sportsmanDTOs.size(), 1);
        Assert.assertEquals(foundSportsman, sportsman);
    }

    @Test
    public void testGetBySurname() {
        List<SportsmanDTO> sportsmanDTOs = sportsmanFacade.getBySurname("Sportsman 1 surname");
        Sportsman foundSportsman = beanMappingService.mapTo(sportsmanDTOs.get(0), Sportsman.class);

        Mockito.verify(sportsmanService).findBySurname("Sportsman 1 surname");
        Assert.assertNotNull(sportsmanDTOs);
        Assert.assertEquals(sportsmanDTOs.size(), 1);
        Assert.assertEquals(foundSportsman, sportsman);
    }

    @Test
    public void testGetAll() {
        List<SportsmanDTO> sportsmanDTOs = sportsmanFacade.getAll();

        Mockito.verify(sportsmanService).findAll();
        Assert.assertNotNull(sportsmanDTOs);
        Assert.assertEquals(sportsmanDTOs.size(), 1);
    }

    @Test
    public void testUpdate() {
        SportsmanUpdateDTO sportsmanUpdateDTO = beanMappingService.mapTo(sportsman, SportsmanUpdateDTO.class);
        sportsmanUpdateDTO.setId(1L);

        sportsmanFacade.update(sportsmanUpdateDTO);

        Mockito.verify(sportsmanService).update(sportsman);
    }

    @Test
    public void testDelete() {
        SportsmanDTO sportsmanDTO = sportsmanFacade.getById(1L);
        sportsmanDTO.setId(1L);

        sportsmanFacade.delete(sportsmanDTO.getId());

        Mockito.verify(sportsmanService).delete(sportsman);
    }

}
