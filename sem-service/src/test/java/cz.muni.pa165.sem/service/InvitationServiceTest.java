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

import java.util.List;

import static org.mockito.Matchers.argThat;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        sportsman.setName("SportsmanName");
        sportsman.setSurname("SportsmanSurname");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail("email@email");
        sportsman.setPassword("safe password");
        
        eventAdmin = new Sportsman();
        eventAdmin .setName("AdminSportsmanName");
        eventAdmin .setSurname("AdminSportsmanSurname");
        eventAdmin .setBirthDate(Calendar.getInstance());
        eventAdmin .setEmail("adm@email");
        eventAdmin .setPassword("Admins safe password");
        
        anotherSportsman = new Sportsman();
        anotherSportsman.setName("anotherSportsmanName");
        
        sport = new Sport();
        sport.setDescription("Sport description");
        sport.setName("SportName");
        
        
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
        Mockito.when(sportsmanDAOMock.findById(sportsman.getId()+anotherSportsman.getId()+eventAdmin.getId())).thenReturn(null); 
    
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(null);
    }

    @Test
    public void inviteWithEventIdNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(event.getId()-1, sportsman.getId());
    }
    
    @Test
    public void inviteWithSportsmanIdNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(event.getId(), sportsman.getId()-1);
    }
    
    @Test
    public void inviteCallsInviteTest(){
        invitationService.invite(event.getId(), sportsman.getId()-1);
        //ALEBO TAKTO ? verify(invitationService.invite(event, sportsman), times(1));
        verify(invitationService, times(1)).invite(event, sportsman);
    }
    
    @Test
    public void inviteEventNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(null, sportsman);
    }
    
    @Test
    public void inviteSportsmanNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(event, null);
    }
    
    @Test
    public void inviteSportsmanAlsoAdmin(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.invite(event, eventAdmin);
    }
    
    @Test
    public void inviteSportsmanAlreadyAdded(){
        expectedException.expect(IllegalArgumentException.class);
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
        Invitation result = invitationService.invite(event, sportsman);
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(invitation);
        
        verify(emailServiceMock, times(1)).sendInvitationMessage(invitation);
        invitation.setState(InvitationState.REINVITED);
        assertEquals(invitation, result);
    }
    
    @Test
    public void inviteAlreadyREINVITED(){
        invitation.setState(InvitationState.REINVITED);
        Invitation result = invitationService.invite(event, sportsman);
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(invitation);
        
        verify(emailServiceMock, times(0)).sendInvitationMessage(invitation);
        invitation.setState(InvitationState.ALREADY_INVITED);
        assertEquals(invitation, result);
    }
    
    @Test
    public void inviteALREADY_INVITED(){
        invitation.setState(InvitationState.ALREADY_INVITED);
        Invitation result = invitationService.invite(event, sportsman);
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(invitation);
        
        verify(emailServiceMock, times(0)).sendInvitationMessage(invitation);
        assertEquals(invitation, result);
    }
    
    @Test
    public void acceptInvitationNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.accept(null);
    }
    
    //Finished means Accepted or Declined
    @Test
    public void acceptFinishedACCEPTED(){
        invitation.setState(InvitationState.ACCEPTED);
        expectedException.expect(IllegalArgumentException.class);
        invitationService.accept(invitation);
    }
    
    @Test
    public void acceptFinishedDECLINED(){
        invitation.setState(InvitationState.DECLINED);
        expectedException.expect(IllegalArgumentException.class);
        invitationService.accept(invitation);
    }
    
    @Test
    public void acceptAddingParticipants(){
        Invitation result = invitationService.accept(invitation);
        verify(event, times(1)).getParticipants().add(sportsman);
        verify(eventDAOMock, times(1)).update(event);
        invitation.setState(InvitationState.ACCEPTED);
        verify(notificationServiceMock, times(1)).notifyInvitationAccepted(invitation);
        assertEquals(invitation, result);
    }
    
    @Test
    public void declineInvitationNull(){
        expectedException.expect(IllegalArgumentException.class);
        invitationService.decline(null);
    }
    
    @Test
    public void declineInvitationAlreadyDECLINED(){
        invitation.setState(InvitationState.DECLINED);
        expectedException.expect(IllegalArgumentException.class);
        invitationService.decline(invitation);
    }
    
    @Test
    public void declineInvitationAlreadyACCEPTED(){
        invitation.setState(InvitationState.ACCEPTED);
        expectedException.expect(IllegalArgumentException.class);
        Invitation result = invitationService.decline(invitation);
        verify(event, times(1)).getParticipants().remove(sportsman);
        verify(eventDAOMock, times(1)).update(event);
        invitation.setState(InvitationState.DECLINED);
        //DALA BY SA PRIDAT NOTIFIKACIA PRI DECLINE
        //verify(notificationServiceMock, times(1)).notifyInvitationDeclined(invitation);
        assertEquals(invitation, result);
    }

}
