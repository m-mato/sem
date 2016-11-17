package cz.muni.pa165.sem.utils;

import cz.muni.pa165.sem.entity.Sportsman;

/**
 * @author Matej Majdis
 */
public class MessageGenerator {

	public static String generateInvitationBody(Sportsman admin, String eventName, Sportsman invitee) {
		String greeting = "Hello " + invitee.getName() + "\n\n";
		String message = admin.getName() + " " + admin.getSurname() + " just invited you to Event: " + eventName + "\n";
		String joinLinnk = "If you want to join this event just click here: TODO-URL\n";
		String manageLink = "If you want to manage your invitations click here: TODO-URL\n\n";
		String bye = "Best regards\nSport Events Manager";

		return greeting + message + joinLinnk + manageLink + bye;
	}
}
