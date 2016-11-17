package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;

/**
 * @author Matej Majdis
 */
public interface InvitationService {

	Invitation invite(Event event, Sportsman invitee);
}
