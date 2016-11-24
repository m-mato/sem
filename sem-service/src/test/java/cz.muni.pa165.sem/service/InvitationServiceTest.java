package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.*;
import cz.muni.pa165.sem.entity.*;
import cz.muni.pa165.sem.utils.InvitationState;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

import static org.mockito.Matchers.argThat;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.validation.constraints.AssertTrue;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
@RunWith(MockitoJUnitRunner.class)
public class InvitationServiceTest {

    @InjectMocks
    private final InvitationService invitationService = new InvitationServiceImpl();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private InvitationDAO invitationDAOMock;

    @Mock
    private EventDAO eventDAOMock;

    @Mock
    private SportsmanDAO sportsmanDAOMock;

    @Mock
    private EmailService emailServiceMock;

    @Mock
    private NotificationService notificationServiceMock;
    
    private Sport sport;
    private Event event;
    private Sportsman eventAdmin;
    private Sportsman sportsman;
    private Sportsman anotherSportsman;
    private Invitation invitation;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        sportsman = new Sportsman();
        sportsman.setId((long) 2);
        sportsman.setName("SportsmanName");
        sportsman.setSurname("SportsmanSurname");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail("email@email");
        sportsman.setPassword("safe password");
        
        eventAdmin = new Sportsman();
        eventAdmin.setId((long) 1);
        eventAdmin.setName("AdminSportsmanName");
        eventAdmin.setSurname("AdminSportsmanSurname");
        eventAdmin.setBirthDate(Calendar.getInstance());
        eventAdmin.setEmail("adm@email");
        eventAdmin.setPassword("Admins safe password");
        
        anotherSportsman = new Sportsman();
        anotherSportsman.setId((long) 3);
        anotherSportsman.setName("anotherSportsmanName");
        anotherSportsman.setSurname("anotherSportsmanSurname");
        anotherSportsman.setBirthDate(Calendar.getInstance());
        anotherSportsman.setEmail("anotherSportsman@email");
        anotherSportsman.setPassword("anotherSportsman safe password");

        
        sport = new Sport();
        sport.setDescription("Sport description");
        sport.setName("SportName");
        
        event = Mockito.spy(new Event());
        event.setId((long) 4);
        event.setName("EventName");
        event.setDescription("New event: EventName");
        event.setDate(Calendar.getInstance());
        event.setAdmin(eventAdmin);
        Integer capacity = 5;
        event.setCapacity(capacity);
        event.setSport(sport);
        event.setAddress("EventAddress");
        event.setCity("EventCity");
        event.addParticipant(anotherSportsman);
        
        
        invitation = new Invitation();
        invitation.setEvent(event);
        invitation.setInvitee(sportsman);
        invitation.setState(InvitationState.INVITED);
        
        Mockito.when(eventDAOMock.findById(event.getId())).thenReturn(event);
        Mockito.when(eventDAOMock.findById(argThat(not(event.getId())))).thenReturn(null);
        
        List<Event> events = new ArrayList<>();
        events.add(event);
        Mockito.when(eventDAOMock.findByName(event.getName())).thenReturn(events);
        
        Mockito.when(sportsmanDAOMock.findById(sportsman.getId())).thenReturn(sportsman);
        Mockito.when(sportsmanDAOMock.findById(anotherSportsman.getId())).thenReturn(anotherSportsman);
        Mockito.when(sportsmanDAOMock.findById(eventAdmin.getId())).thenReturn(eventAdmin);
        Mockito.when(sportsmanDAOMock.findById(Long.MAX_VALUE)).thenReturn(null);

        Mockito.when(invitationDAOMock.findById(argThat(not(1l)))).thenReturn(null);
        Mockito.when(invitationDAOMock.findById(1L)).thenReturn(invitation);
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(null);
        Mockito.when(invitationDAOMock.findAll()).thenReturn(Collections.singletonList(invitation));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteWithNotExistingEvent(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(event.getId()-1, sportsman.getId());
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteNotExistingSportsman(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(event.getId(), Long.MAX_VALUE);
    }
    
//    @Test
//    public void inviteCallsInviteTest(){
//        invitationService.invite(event.getId(), sportsman.getId()-1);
//        //ALEBO TAKTO ? verify(invitationService.invite(event, sportsman), times(1));
//        verify(invitationService, times(1)).invite(event, sportsman);
//    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteEventNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(null, sportsman);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteSportsmanNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(event, null);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void inviteSportsmanAlsoAdmin(){
        expectedException.expect(IllegalStateException.class);
        invitationService.invite(event, eventAdmin);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteSportsmanAlreadyAdded(){
        expectedException.expect(IllegalArgumentException.class);
        Invitation invitationR = invitationService.invite(event, anotherSportsman);
        invitationService.accept(invitationR);
        invitationService.invite(event, anotherSportsman);
    }
    
    @Test
    public void inviteNewInvitation(){
        invitationService.invite(event, sportsman);
        verify(invitationDAOMock, times(1)).create(invitation);
        verify(emailServiceMock, times(1)).sendInvitationMessage(invitation);
    }
    
    @Test
    public void inviteAlreadyINVITED(){
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(invitation);
        Invitation result = invitationService.invite(event, sportsman);
        verify(emailServiceMock, times(1)).sendInvitationMessage(invitation);
        invitation.setState(InvitationState.REINVITED);
        assertEquals(invitation, result);
    }
    
    @Test
    public void inviteAlreadyREINVITED(){
        invitation.setState(InvitationState.REINVITED);
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(invitation);
        Invitation result = invitationService.invite(event, sportsman);
        verify(emailServiceMock, times(0)).sendInvitationMessage(invitation);
        invitation.setState(InvitationState.ALREADY_INVITED);
        assertEquals(invitation, result);
    }
    
    @Test
    public void inviteALREADY_INVITED(){
        invitation.setState(InvitationState.ALREADY_INVITED);
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(invitation);
        Invitation result = invitationService.invite(event, sportsman);
        verify(emailServiceMock, times(0)).sendInvitationMessage(invitation);
        assertEquals(invitation, result);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void acceptInvitationNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.accept(null);
    }
    
    //Finished means Accepted or Declined
    @Test(expectedExceptions = IllegalStateException.class)
    public void acceptFinishedACCEPTED(){
        invitation.setState(InvitationState.ACCEPTED);
        expectedException.expect(IllegalStateException.class);
        invitationService.accept(invitation);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void acceptFinishedDECLINED(){
        invitation.setState(InvitationState.DECLINED);
        expectedException.expect(IllegalStateException.class);
        invitationService.accept(invitation);
    }
    
    @Test
    public void acceptAddingParticipants(){
        Invitation result = invitationService.accept(invitation);
        //verify(event, times(1)).getParticipants().add(sportsman);
        verify(eventDAOMock, times(1)).update(event);
        invitation.setState(InvitationState.ACCEPTED);
        verify(notificationServiceMock, times(1)).notifyInvitationAccepted(invitation);
        assertEquals(invitation, result);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void declineInvitationNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.decline(null);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void declineInvitationAlreadyDECLINED(){
        invitation.setState(InvitationState.DECLINED);
        expectedException.expect(IllegalStateException.class);
        invitationService.decline(invitation);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void declineInvitationAlreadyACCEPTED() {
        invitation.setState(InvitationState.ACCEPTED);
        expectedException.expect(IllegalStateException.class);
        Invitation result = invitationService.decline(invitation);
    }

    @Test
    public void findByIdExisting() {
        Long id = 1L;
        Invitation invitation = invitationService.findById(id);
        Assert.assertNotNull(invitation);
        Mockito.verify(invitationDAOMock, times(1)).findById(id);
    }

    @Test
    public void findByIdNonExisting() {
        Long id = 2L;
        Invitation invitation = invitationService.findById(id);
        Assert.assertNull(invitation);
        Mockito.verify(invitationDAOMock, times(1)).findById(id);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByIdNull() {
        doThrow(new IllegalArgumentException("Trying to find object by null id!"))
                .when(invitationDAOMock)
                .findById(null);

        invitationService.findById(null);
    }

    @Test
    public void findAll(){
        List<Invitation> result = invitationService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Mockito.verify(invitationDAOMock, times(1)).findAll();
    }
}
