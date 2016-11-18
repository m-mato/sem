package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.dao.EventDAO;
import cz.muni.pa165.sem.dao.InvitationDAO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.utils.InvitationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private EmailService emailService;

	@Override
	public Invitation invite(Event event, Sportsman invitee) {

		if (event == null) {
			throw new IllegalArgumentException("Event can not be null");
		}
		if (invitee == null) {
			throw new IllegalArgumentException("Invitee can not be null");
		}

		if(event.getAdmin().equals(invitee)) {
			throw new IllegalStateException("Cannot invite yourself");
		}

		if(event.getParticipants().contains(invitee)) {
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

	private boolean isFinished(Invitation existingInvitation) {
		InvitationState state =  existingInvitation.getState();
		return state.equals(InvitationState.ACCEPTED) || state.equals(InvitationState.DECLINED);
	}

	private Invitation changeInvitationState(Invitation invitation, InvitationState state) {
		invitation.setState(state);
		invitationDAO.update(invitation);

		return invitation;
	}
}
