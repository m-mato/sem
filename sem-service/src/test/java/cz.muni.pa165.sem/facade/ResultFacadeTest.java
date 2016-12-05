package cz.muni.pa165.sem.facade;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Result;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.service.*;
import cz.muni.pa165.sem.service.facade.ResultFacadeImpl;
import cz.muni.pa165.sem.utils.PerformanceUnits;

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
public class ResultFacadeTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private ResultFacadeImpl resultFacade = new ResultFacadeImpl();

    @Mock
    private EventService eventService;

    @Mock
    private ResultService resultService;

    @Mock
    private SportService sportService;

    @Mock
    private SportsmanService sportsmanService;

    @Autowired
    private BeanMappingService beanMappingService;

    private Event event;
    private Result result;
    private Sport sport;
    private Sportsman sportsman;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        resultFacade.setBeanMappingService(beanMappingService);

        Calendar date = Calendar.getInstance();

        sport = new Sport();
        sport.setId(1L);
        sport.setName("Sport 1 name");
        sport.setDescription("Sport 1 description");

        Mockito.when(sportService.findById(1L)).thenReturn(sport);

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

        event = new Event();
        event.setId(1L);
        event.setName("Event 1 name");
        event.setDescription("Event 1 description");
        event.setDate(date);
        event.setSport(sport);
        event.setCapacity(10);
        event.setCity("Event 1 city");
        event.setAddress("Event 1 address");
        event.setAdmin(sportsman);
        event.setParticipants(new HashSet<>());

        Mockito.when(eventService.findById(1L)).thenReturn(event);

        result = new Result();
        result.setPerformance(9.58);
        result.setPerformanceUnit(PerformanceUnits.SECOND);
        result.setPosition(1);
        result.setSportsman(sportsman);
        result.setNote("World record");
        result.setEvent(event);

        Mockito.when(resultService.findById(1L)).thenReturn(result);
        Mockito.when(resultService.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(resultService.findByPerformance(9.58)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultService.findByPerformance(argThat(not(9.58)))).thenReturn(Collections.emptyList());
        Mockito.when(resultService.findByPosition(1)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultService.findByPosition(argThat(not(1)))).thenReturn(Collections.emptyList());
        Mockito.when(resultService.findBySportsman(sportsman)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultService.findBySportsman(argThat(not(sportsman)))).thenReturn(Collections.emptyList());
        Mockito.when(resultService.findByNote("World record")).thenReturn(Collections.singletonList(result));
        Mockito.when(resultService.findByNote(argThat(not("World record")))).thenReturn(Collections.emptyList());
        Mockito.when(resultService.findByEvent(event)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultService.findByEvent(argThat(not(event)))).thenReturn(Collections.emptyList());
        Mockito.when(resultService.findBySport(sport)).thenReturn(Collections.singletonList(result));
        Mockito.when(resultService.findBySport(argThat(not(sport)))).thenReturn(Collections.emptyList());
        Mockito.when(resultService.findAll()).thenReturn(Collections.singletonList(result));
    }

    @Test
    public void testCreate() {
        ResultCreateDTO resultCreateDTO = new ResultCreateDTO();
        resultCreateDTO.setPerformance(9.58);
        resultCreateDTO.setPerformanceUnit(PerformanceUnits.SECOND);
        resultCreateDTO.setPosition(1);
        resultCreateDTO.setSportsman(beanMappingService.mapTo(sportsman, SportsmanDTO.class));
        resultCreateDTO.setNote("World record");
        resultCreateDTO.setEvent(beanMappingService.mapTo(event, EventDTO.class));

        resultFacade.create(resultCreateDTO);
        Result createdResult = beanMappingService.mapTo(resultCreateDTO, Result.class);

        Mockito.verify(resultService).create(createdResult);
    }

    @Test
    public void testFindExistingById() {
        ResultDTO resultDTO = resultFacade.findById(1L);
        Result foundResult = beanMappingService.mapTo(resultDTO, Result.class);

        Mockito.verify(resultService).findById(1L);
        Assert.assertEquals(foundResult, result);
    }

    @Test
    public void testFindNonExistingById() {
        ResultDTO resultDTO = resultFacade.findById(2L);

        Mockito.verify(resultService).findById(2L);
        Assert.assertNull(resultDTO);
    }

    @Test
    public void testFindBySportsman() {
        SportsmanDTO sportsmanDTO = beanMappingService.mapTo(sportsman, SportsmanDTO.class);
        List<ResultDTO> resultDTOs = resultFacade.findBySportsman(sportsmanDTO);
        Result foundResult = beanMappingService.mapTo(resultDTOs.get(0), Result.class);

        Mockito.verify(resultService).findBySportsman(sportsman);
        Assert.assertNotNull(resultDTOs);
        Assert.assertEquals(resultDTOs.size(), 1);
        Assert.assertEquals(foundResult, result);
    }

    @Test
    public void testFindByEvent() {
        EventDTO eventDTO = beanMappingService.mapTo(event, EventDTO.class);
        List<ResultDTO> resultDTOs = resultFacade.findByEvent(eventDTO);
        Result foundResult = beanMappingService.mapTo(resultDTOs.get(0), Result.class);

        Mockito.verify(resultService).findByEvent(event);
        Assert.assertNotNull(resultDTOs);
        Assert.assertEquals(resultDTOs.size(), 1);
        Assert.assertEquals(foundResult, result);
    }

    @Test
    public void testFindBySport() {
        SportDTO sportDTO = beanMappingService.mapTo(sport, SportDTO.class);
        List<ResultDTO> resultDTOs = resultFacade.findBySport(sportDTO);
        Result foundResult = beanMappingService.mapTo(resultDTOs.get(0), Result.class);

        Mockito.verify(resultService).findBySport(sport);
        Assert.assertNotNull(resultDTOs);
        Assert.assertEquals(resultDTOs.size(), 1);
        Assert.assertEquals(foundResult, result);
    }

    @Test
    public void testFindByPerformance() {
        List<ResultDTO> resultDTOs = resultFacade.findByPerformance(9.58);
        Result foundResult = beanMappingService.mapTo(resultDTOs.get(0), Result.class);

        Mockito.verify(resultService).findByPerformance(9.58);
        Assert.assertNotNull(resultDTOs);
        Assert.assertEquals(resultDTOs.size(), 1);
        Assert.assertEquals(foundResult, result);
    }

    @Test
    public void testFindByPosition() {
        List<ResultDTO> resultDTOs = resultFacade.findByPosition(1);
        Result foundResult = beanMappingService.mapTo(resultDTOs.get(0), Result.class);

        Mockito.verify(resultService).findByPosition(1);
        Assert.assertNotNull(resultDTOs);
        Assert.assertEquals(resultDTOs.size(), 1);
        Assert.assertEquals(foundResult, result);
    }

    @Test
    public void testFindByNote() {
        List<ResultDTO> resultDTOs = resultFacade.findByNote("World record");
        Result foundResult = beanMappingService.mapTo(resultDTOs.get(0), Result.class);

        Mockito.verify(resultService).findByNote("World record");
        Assert.assertNotNull(resultDTOs);
        Assert.assertEquals(resultDTOs.size(), 1);
        Assert.assertEquals(foundResult, result);
    }

    @Test
    public void testGetAll() {
        List<ResultDTO> resultDTOs = resultFacade.findAll();

        Mockito.verify(resultService).findAll();
        Assert.assertNotNull(resultDTOs);
        Assert.assertEquals(resultDTOs.size(), 1);
    }

    @Test
    public void testUpdate() {
        ResultUpdateDTO resultUpdateDTO = beanMappingService.mapTo(resultFacade.findById(1L), ResultUpdateDTO.class);
        resultUpdateDTO.setId(1L);

        resultFacade.update(resultUpdateDTO);

        Mockito.verify(resultService).update(result);
    }

    @Test
    public void testDelete() {
        ResultDTO resultDTO = beanMappingService.mapTo(resultFacade.findById(1L), ResultDTO.class);
        resultDTO.setId(1L);

        resultFacade.delete(resultDTO.getId());

        Mockito.verify(resultService).delete(result);
    }

}
