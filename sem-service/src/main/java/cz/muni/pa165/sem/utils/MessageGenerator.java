package cz.muni.pa165.sem.utils;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sportsman;

/**
 * @author Matej Majdis
 */
public class MessageGenerator {

	private static final String BYE = "Best regards\nSport Events Manager";

	public static String generateInvitationBody(Sportsman admin, String eventName, Sportsman invitee) {

		String greeting = "Hello " + invitee.getName() + "\n\n";
		String message = admin.getName() + " " + admin.getSurname() + " just invited you to Event: " + eventName + "\n";
		String joinLink = "If you want to join this event just click here: TODO-URL\n";
		String manageLink = "If you want to manage your invitations click here: TODO-URL\n\n";

		return greeting + message + joinLink + manageLink + BYE;
	}

	public static String generateInvitationAcceptBody(String sportsmanName, Sportsman invitee, String eventName) {

		String greeting = "Hello " + sportsmanName + "\n\n";
		String message = invitee.getName() + " " + invitee.getSurname() + " just joined Event: " + eventName + ", which you are participating on\n";
		String link = "If you want to review this event just click here: TODO-URL\n\n";

		return greeting + message + link + BYE;
	}

	public static String generateEventEditBody(String sportsmanName, Event event) {

		String greeting = "Hello " + sportsmanName + "\n\n";
		String message = event.getAdmin().getName() + " " + event.getAdmin().getSurname() + " just edited Event: " + event.getName() + "\n";
		String link = "If you want to review changes just click here: TODO-URL\n\n";

		return greeting + message + link + BYE;
	}

	public static String generateEventCancelBody(String sportsmanName, Event event) {

		String greeting = "Hello " + sportsmanName + "\n\n";
		String message = event.getAdmin().getName() + " " + event.getAdmin().getSurname() + " just cancel Event: " + event.getName() + "\n";
		String link = "If you want to review changes just click here: TODO-URL\n\n";

		return greeting + message + link + BYE;
	}
}
