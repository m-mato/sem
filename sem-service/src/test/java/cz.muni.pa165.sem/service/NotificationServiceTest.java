package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.*;
import cz.muni.pa165.sem.utils.InvitationState;

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
import static org.mockito.Mockito.*;

/**
 * @author Veronika Aksamitova
 */
@ContextConfiguration(locations = "classpath:service-test-context.xml")
public class NotificationServiceTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private NotificationService notificationService = new NotificationServiceImpl();

    @Mock
    private EmailService emailServiceMock;

    private Invitation invitationWithoutParticipants;
    private Invitation invitationWithParticipants;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Mockito.when(emailServiceMock.sendMessage(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        invitationWithoutParticipants = getInvitation(getSportsman("invitee"), getEvent("event"));
        invitationWithParticipants = getInvitation(getSportsman("invitee"), addParticipants(getEvent("event")));
    }

    @Test
    public void notifyInvitationAcceptedNoParticipants() {
        boolean result = notificationService.notifyInvitationAccepted(invitationWithoutParticipants);
        verify(emailServiceMock, times(1)).sendMessage(Mockito.anyString(), Mockito.anyString(),
                eq(invitationWithoutParticipants.getEvent().getAdmin().getEmail()));
        Assert.assertEquals(result, true);
    }

    @Test
    public void notifyInvitationAcceptedWithParticipants() {
        notificationService.notifyInvitationAccepted(invitationWithParticipants);
        verify(emailServiceMock, times(3)).sendMessage(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
       verify(emailServiceMock, times(1)).sendMessage(Mockito.anyString(), Mockito.anyString(),
              eq(invitationWithParticipants.getEvent().getAdmin().getEmail()));
    }

    @Test
    public void notifyEventEditedNoParticipants() {
        Event eventNoParticipants = invitationWithoutParticipants.getEvent();
        boolean result = notificationService.notifyEventEdited(eventNoParticipants.getParticipants(), eventNoParticipants);
        verify(emailServiceMock, times(0)).sendMessage(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString());
        Assert.assertEquals(true,result);
    }

    @Test
    public void notifyEventEditedWithParticipants() {
        Event event = invitationWithParticipants.getEvent();
        notificationService.notifyEventEdited(event.getParticipants(), event);
        verify(emailServiceMock, times(2)).sendMessage(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString());
    }

    private Invitation getInvitation(Sportsman invitee, Event event) {
        Invitation invitation = new Invitation();
        invitation.setEvent(event);
        invitation.setState(InvitationState.INVITED);
        invitation.setInvitee(invitee);
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
        return event;
    }

    private Event addParticipants(Event event)
    {
        event.addParticipant(getSportsman("participant 1"));
        event.addParticipant(getSportsman("participant 2"));
        return event;
    }

    private Sportsman getSportsman(String name) {
        Sportsman sportsman = new Sportsman();
        sportsman.setName(name);
        sportsman.setSurname("sportsmanSurname");
        sportsman.setBirthDate(Calendar.getInstance());
        sportsman.setEmail(name.replaceAll("\\s","") + "@email");
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
