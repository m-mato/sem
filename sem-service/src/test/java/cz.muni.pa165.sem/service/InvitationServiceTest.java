package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.*;
import cz.muni.pa165.sem.entity.*;
import cz.muni.pa165.sem.utils.InvitationState;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.not;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class InvitationServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private final InvitationService invitationService = new InvitationServiceImpl();

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
        
        sportsman = getSportsman("sportsman");
        eventAdmin = getSportsman("eventAdmin");
        anotherSportsman = getSportsman("anotherSportsman");

        sport = new Sport();
        sport.setDescription("Sport description");
        sport.setName("SportName");
        
        event = getEvent();
        invitation = getInvitation(sportsman, event);

        
        Mockito.when(eventDAOMock.findById(event.getId())).thenReturn(event);
        Mockito.when(eventDAOMock.findById(argThat(not(event.getId())))).thenReturn(null);
        
        List<Event> events = new ArrayList<>();
        events.add(event);
        Mockito.when(eventDAOMock.findByName(event.getName())).thenReturn(events);
        Mockito.when(eventDAOMock.findById(5L)).thenReturn(event);
        Mockito.when(eventDAOMock.findById(10L)).thenReturn(null);

        Mockito.when(sportsmanDAOMock.findById(1L)).thenReturn(sportsman);
        Mockito.when(sportsmanDAOMock.findById(2L)).thenReturn(anotherSportsman);
        Mockito.when(sportsmanDAOMock.findById(3L)).thenReturn(eventAdmin);
        Mockito.when(sportsmanDAOMock.findById(4L)).thenReturn(null);

        Mockito.when(invitationDAOMock.findById(argThat(not(1L)))).thenReturn(null);
        Mockito.when(invitationDAOMock.findById(1L)).thenReturn(invitation);
        Mockito.when(invitationDAOMock.findByEventAndInvitee(event, sportsman)).thenReturn(null);
        Mockito.when(invitationDAOMock.findAll()).thenReturn(Collections.singletonList(invitation));
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteWithNotExistingEvent(){
        invitationService.invite(10L, 2L);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteNotExistingSportsman(){
        invitationService.invite(5L, 4L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteEventNull(){
        invitationService.invite(null, sportsman);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteSportsmanNull(){
        invitationService.invite(event, null);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void inviteSportsmanAlsoAdmin(){
        invitationService.invite(event, eventAdmin);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void inviteSportsmanAlreadyAdded(){
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
        assertEquals(invitation, result);
    }

    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void acceptInvitationNull(){
        invitationService.accept(null);
    }
    
    //Finished means Accepted or Declined
    @Test(expectedExceptions = IllegalStateException.class)
    public void acceptFinishedACCEPTED(){
        invitation.setState(InvitationState.ACCEPTED);
        invitationService.accept(invitation);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void acceptFinishedDECLINED(){
        invitation.setState(InvitationState.DECLINED);
        invitationService.accept(invitation);
    }

    @Test(expectedExceptions = NullPointerException.class)
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
        invitationService.decline(null);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void declineInvitationAlreadyDECLINED(){
        invitation.setState(InvitationState.DECLINED);
        invitationService.decline(invitation);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void declineInvitationAlreadyACCEPTED() {
        invitation.setState(InvitationState.ACCEPTED);
        invitationService.decline(invitation);
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

    private Invitation getInvitation(Sportsman sportsman, Event event) {
        Invitation invitation = new Invitation();
        invitation.setInvitee(sportsman);
        invitation.setEvent(event);
        invitation.setState(InvitationState.INVITED);
        return invitation;
    }

    private Sportsman getSportsman(String name) {
        Sportsman sportsman= new Sportsman();
        sportsman.setName(name);
        sportsman.setSurname(name +" Surname");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail(name.replace(" ","") +"@email");
        sportsman.setPassword(name +"s safe password");
        return sportsman;
    }

    private Event getEvent() {
        Event event = new Event();
        event.setName(" Name");
        event.setDescription("New event: EventName");
        event.setDate(Calendar.getInstance());
        event.setAdmin(eventAdmin);
        Integer capacity = 5;
        event.setCapacity(capacity);
        event.setSport(sport);
        event.setAddress("EventAddress");
        event.setCity("EventCity");
        event.addParticipant(anotherSportsman);
        return event;
    }

}
