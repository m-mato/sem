package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.EventDAO;
import cz.muni.pa165.sem.dao.InvitationDAO;
import cz.muni.pa165.sem.dao.SportsmanDAO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.utils.InvitationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matej Majdis
 */
@Service
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private InvitationDAO invitationDAO;

	@Autowired
	private EventDAO eventDAO;

	@Autowired
	private SportsmanDAO sportsmanDAO;

	@Autowired
	private EmailService emailService;

	@Autowired
	private NotificationService notificationService;

	@Override
	public Invitation invite(long eventId, long sportsmanId) {

		Event event = eventDAO.findById(eventId);
		if (event == null) {
			throw new IllegalArgumentException("Event not found");
		}

		Sportsman sportsman = sportsmanDAO.findById(sportsmanId);
		if (sportsman == null) {
			throw new IllegalArgumentException("Sportsman not found");
		}

		return invite(event, sportsman);
	}

	@Override
	public Invitation invite(Event event, Sportsman invitee) {

		if (event == null) {
			throw new IllegalArgumentException("Event can not be null");
		}
		if (invitee == null) {
			throw new IllegalArgumentException("Invitee can not be null");
		}

		if (event.getAdmin().equals(invitee)) {
			throw new IllegalStateException("Cannot invite yourself");
		}

		if (event.getParticipants().contains(invitee)) {
			return null;
		}

		//check and process existing invitations
		Invitation existingInvitation = invitationDAO.findByEventAndInvitee(event, invitee);
		if (existingInvitation != null && !isFinished(existingInvitation)) {
			if (InvitationState.INVITED.equals(existingInvitation.getState())) {
				emailService.sendInvitationMessage(existingInvitation);
				return changeInvitationState(existingInvitation, InvitationState.REINVITED);
			} else if (InvitationState.REINVITED.equals(existingInvitation.getState())) {
				return changeInvitationState(existingInvitation, InvitationState.ALREADY_INVITED);
			}
			return existingInvitation;
		}

		//if there is no existing invitation || is finished create new
		Invitation newInvitation = new Invitation();
		newInvitation.setState(InvitationState.INVITED);
		newInvitation.setEvent(event);
		newInvitation.setInvitee(invitee);
		invitationDAO.create(newInvitation);

		emailService.sendInvitationMessage(newInvitation);

		return newInvitation;
	}

	@Override
	public Invitation accept(Invitation invitation) {

		if (invitation == null) {
			throw new IllegalArgumentException("Invitation can not be null");
		}

		if (isFinished(invitation)) {
			throw new IllegalStateException("Invitation is already in state: " + invitation.getState());
		}

		Event event = invitation.getEvent();
		event.getParticipants().add(invitation.getInvitee());
		eventDAO.update(event);

		notificationService.notifyInvitationAccepted(invitation);

		return changeInvitationState(invitation, InvitationState.ACCEPTED);
	}

	@Override
	public Invitation decline(Invitation invitation) {

		if (invitation == null) {
			throw new IllegalArgumentException("Invitation can not be null");
		}

		if (isFinished(invitation)) {
			throw new IllegalStateException("Invitation is already in state: " + invitation.getState());
		}

		Event event = invitation.getEvent();
		event.getParticipants().remove(invitation.getInvitee());
		eventDAO.update(event);

		//notificationService.notifyInvitationDeclined(invitation);

		return changeInvitationState(invitation, InvitationState.DECLINED);
	}

	@Override
	public Invitation findById(Long id) {
		try {
			return invitationDAO.findById(id);
		} catch (Exception e) {
			throw new DataRetrievalFailureException("Failed to find invitation by id " + id + ", exception: ", e);
		}
	}

	@Override
	public List<Invitation> findAll() {
		try {
			return invitationDAO.findAll();
		} catch (Exception e) {
			throw new DataRetrievalFailureException("Failed to find all invitations, exception: ", e);
		}
	}

	private boolean isFinished(Invitation invitation) {
		InvitationState state = invitation.getState();
		return state.equals(InvitationState.ACCEPTED) || state.equals(InvitationState.DECLINED);
	}

	private Invitation changeInvitationState(Invitation invitation, InvitationState state) {
		invitation.setState(state);
		invitationDAO.update(invitation);

		return invitation;
	}
}