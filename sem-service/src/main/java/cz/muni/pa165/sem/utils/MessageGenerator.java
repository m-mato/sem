package cz.muni.pa165.sem.utils;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;

/**
 * @author Matej Majdis
 */
public class MessageGenerator {

	private static final String BYE = "Best regards\nSport Events Manager";

	public static String generateInvitationBody(Invitation invitation) {

		String greeting = "Hello " + invitation.getInvitee().getName() + "\n\n";
		String message = "You was just invited to Event: " + invitation.getEvent().getName() + " in " + invitation.getEvent().getCity() + ".\n";
		String desc = "Description: " + invitation.getEvent().getDescription() + ".\n";
		String manageLink = "If you want to manage your invitations click here: http://localhost:8080/pa165/my-account\n";
		String joinLink = "If you want to join this event click here: http://localhost:8080/pa165/accept/" + invitation.getId() + "\n\n";

		return greeting + message + desc + manageLink + joinLink + BYE;
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
		String link = "If you want to review changes just click here: http://localhost:8080/pa165/events/" + event.getId() + "\n\n";

		return greeting + message + link + BYE;
	}

	public static String generateEventCancelBody(String sportsmanName, Event event) {

		String greeting = "Hello " + sportsmanName + "\n\n";
		String message = event.getAdmin().getName() + " " + event.getAdmin().getSurname() + " just cancel Event: " + event.getName() + "\n";
		String link = "If you want to choose from some other events just click here: http://localhost:8080/pa165/events\n\n";

		return greeting + message + link + BYE;
	}
}
