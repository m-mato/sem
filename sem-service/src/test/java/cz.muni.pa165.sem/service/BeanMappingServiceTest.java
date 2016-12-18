package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.entity.*;
import cz.muni.pa165.sem.utils.InvitationState;
import cz.muni.pa165.sem.utils.PerformanceUnits;

import org.dozer.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private final BeanMappingService beanMappingService = new BeanMappingServiceImpl();

    @Mock
    private Mapper dozer;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMapSportToDTO() {
        Sport sport = getSport("sport");
        beanMappingService.mapTo(sport, SportDTO.class);
        Mockito.verify(dozer, times(1)).map(sport, SportDTO.class);
    }

    @Test
    public void testMapEventToDTO() {
        Event event = getEvent("EventName");
        beanMappingService.mapTo(event, EventDTO.class);
        Mockito.verify(dozer, times(1)).map(event, EventDTO.class);
    }

    @Test
    public void testMapSportsmanToDTO() {
        Sportsman sportsman = getSportsman("sportsman");
        beanMappingService.mapTo(sportsman, SportsmanDTO.class);
        Mockito.verify(dozer, times(1)).map(sportsman, SportsmanDTO.class);
    }

    @Test
    public void testMapInvitationToDTO() {
        Invitation invitation = getInvitation(getSportsman("invitee"), getEvent("EventName"));
        beanMappingService.mapTo(invitation, InvitationDTO.class);
        Mockito.verify(dozer, times(1)).map(invitation, InvitationDTO.class);
    }

    @Test
    public void testMapResultToDTO() {
        Result result = getResult(getSportsman("winner"), getEvent("EventName"));
        beanMappingService.mapTo(result, ResultDTO.class);
        Mockito.verify(dozer, times(1)).map(result, ResultDTO.class);
    }

    @Test
    public void testMapListEventToDTO() {
        List<Event> events = new ArrayList<>();
        Event event1 = getEvent("Event 1");
        Event event2 = getEvent("Event 2");
        events.add(event1);
        events.add(event2);
        beanMappingService.mapTo(events, EventDTO.class);
        Mockito.verify(dozer, times(1)).map(event1, EventDTO.class);
        Mockito.verify(dozer, times(1)).map(event2, EventDTO.class);
    }

    @Test
    public void testMapListInvitationToDTO() {
        List<Invitation>  invitations = new ArrayList<>();
        Invitation invitation1 = getInvitation(getSportsman("sportsman 1"), getEvent("event 1"));
        Invitation invitation2 = getInvitation(getSportsman("sportsman 2"), getEvent("event 2"));
        invitations.add(invitation1);
        invitations.add(invitation2);
        beanMappingService.mapTo(invitations, InvitationDTO.class);
        Mockito.verify(dozer, times(1)).map(invitation1, InvitationDTO.class);
        Mockito.verify(dozer, times(1)).map(invitation2, InvitationDTO.class);
    }

    @Test
    public void testMapListResultToDTO() {
        List<Result>  results = new ArrayList<>();
        Result result1 = getResult(getSportsman("sportsman 1"), getEvent("event 1"));
        Result result2 = getResult(getSportsman("sportsman 2"), getEvent("event 2"));
        results.add(result1);
        results.add(result2);
        beanMappingService.mapTo(results,ResultDTO.class);
        Mockito.verify(dozer, times(1)).map(result1, ResultDTO.class);
        Mockito.verify(dozer, times(1)).map(result2, ResultDTO.class);
    }

    @Test
    public void testMapListSportToDTO() {
        List<Sport>  sports = new ArrayList<>();
        Sport sport1 = getSport("sport 1");
        Sport sport2 = getSport("sport 2");
        sports.add(sport1);
        sports.add(sport2);
        beanMappingService.mapTo(sports,SportDTO.class);
        Mockito.verify(dozer, times(1)).map(sport1, SportDTO.class);
        Mockito.verify(dozer, times(1)).map(sport2, SportDTO.class);
    }

    @Test
    public void testMapListSportsmanToDTO() {
        List<Sportsman>  sportsmans = new ArrayList<>();
        Sportsman sportsman1 = getSportsman("sportsman 1");
        Sportsman sportsman2 = getSportsman("sportsman 2");
        sportsmans.add(sportsman1);
        sportsmans.add(sportsman2);
        beanMappingService.mapTo(sportsmans,SportsmanDTO.class);
        Mockito.verify(dozer, times(1)).map(sportsman1, SportsmanDTO.class);
        Mockito.verify(dozer, times(1)).map(sportsman2, SportsmanDTO.class);
    }

    private Result getResult(Sportsman sportsman, Event event) {
        Result result = new Result();
        result.setEvent(event);
        result.setSportsman(sportsman);
        result.setPosition(1);
        result.setPerformanceUnit(PerformanceUnits.METER);
        result.setPerformance(5D);
        result.setNote("note");
        return result;
    }

    private Invitation getInvitation(Sportsman invitee, Event event) {
        Invitation invitation = new Invitation();
        invitation.setEvent(event);
        invitation.setInvitee(invitee);
        invitation.setState(InvitationState.INVITED);
        return invitation;
    }

    private Event getEvent(String name){
        Event event = new Event();
        event.setName(name);
        event.setDescription("New event: EventName");
        event.setDate(Calendar.getInstance());
        event.setAdmin(getSportsman("eventAdmin"));
        Integer capacity = 5;
        event.setCapacity(capacity);
        event.setSport(getSport("sport"));
        event.setAddress("EventAddress");
        event.setCity("EventCity");
        event.addParticipant(getSportsman("sportsman"));
        return event;
    }

    private Sportsman getSportsman(String name) {
        Sportsman sportsman = new Sportsman();
        sportsman.setName(name);
        sportsman.setSurname("sportsmanSurname");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail("sportsman@email");
        sportsman.setPassword("sportsman safe password");

        return sportsman;
    }

    private Sport getSport(String name){
        Sport sport = new Sport();
        sport.setName(name);
        sport.setDescription("sport description");
        return sport;
    }

}
