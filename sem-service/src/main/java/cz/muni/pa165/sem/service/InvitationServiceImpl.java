package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.EventDAO;
import cz.muni.pa165.sem.dao.InvitationDAO;
import cz.muni.pa165.sem.dao.ResultDAO;
import cz.muni.pa165.sem.dao.SportsmanDAO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Result;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.utils.InvitationState;
import cz.muni.pa165.sem.utils.PerformanceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

	@Autowired
	private ResultDAO resultDAO;

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

		Result result = new Result();
		result.setPerformanceUnit(PerformanceUnits.SECOND);
		result.setEvent(invitation.getEvent());
		result.setSportsman(invitation.getInvitee());
		result.setPosition(new Integer(-1));
		result.setPerformance(new Double(-1));
		result.setNote("");
		resultDAO.create(result);

		Event event = invitation.getEvent();
		Set<Sportsman> participants = event.getParticipants();
		participants.add(invitation.getInvitee());
		event.setParticipants(participants);
		eventDAO.update(event);

		return changeInvitationState(invitation, InvitationState.ACCEPTED);
	}

	@Override
	public Invitation simpleAccept(Invitation invitation) {
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
	public Invitation findByEventAndInvitee(Event event, Sportsman invitee) {
		try {
			return invitationDAO.findByEventAndInvitee(event, invitee);
		} catch (Exception e) {
			throw new DataRetrievalFailureException("Failed to find invitation", e);
		}
	}

	@Override
	public List<Invitation> findByEvent(Event event) {
		try {
			return invitationDAO.findByEvent(event);
		} catch (Exception e) {
			throw new DataRetrievalFailureException("Failed to find invitations, exception: ", e);
		}
	}

	@Override
	public List<Invitation> findByInvitee(Sportsman invitee) {
		try {
			return invitationDAO.findByInvitee(invitee);
		} catch (Exception e) {
			throw new DataRetrievalFailureException("Failed to find invitations, exception: ", e);
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
