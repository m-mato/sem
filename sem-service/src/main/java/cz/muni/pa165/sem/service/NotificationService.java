package cz.muni.pa165.sem.service;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;

import java.util.Set;

/**
 * @author Matej Majdis
 */
public interface NotificationService {

	boolean notifyInvitationAccepted(Invitation invitation);

	boolean notifyEventEdited(Set<Sportsman> participants, Event event);

	boolean notifyEventCanceled(Set<Sportsman> participants, Event event);
}
