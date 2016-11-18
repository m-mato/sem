package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

/**
 * @author Matej Majdis
 *         This Controller is used only for testing purposes !
 */
@RestController
@RequestMapping("/test")
public class ExampleController {

	@Autowired
	private ExampleService exampleService;

	@Autowired
	private InvitationService invitationService;

	@Autowired
	private EventService eventService;

	@Autowired
	private SportService sportService;

	@Autowired
	private SportsmanService sportsmanService;

	/*
		Test controller for invite user
		{eventId} - id of Event to invite for
		{sportsmanId} - id of Sportsman which will be invited
		@returns - current invitation's state and description

		All info and ids can be found on /data/get-all
	 */
	@RequestMapping("/invite/{eventId}/{sportsmanId}")
	@Transactional
	public String inviteTest(@PathVariable(value = "eventId") String eventId,
							 @PathVariable(value = "sportsmanId") String sportsmanId) {

		Long eventIdLong = Long.valueOf(eventId);
		Long sportsmanIdLong = Long.valueOf(sportsmanId);

		Event event = eventService.findById(eventIdLong);
		if (event == null) {
			return "Event not found";
		}

		Sportsman sportsman = sportsmanService.findById(sportsmanIdLong);
		if (sportsman == null) {
			return "Sportsman not found";
		}

		Invitation invitation = invitationService.invite(event, sportsman);
		if (invitation == null) {
			return sportsman.getName() + " is already member of event : " + event.getName();
		}
		switch (invitation.getState()) {
			case INVITED:
				return sportsman.getName() + " SUCCESSFULLY invited to event : " + event.getName() + ".";
			case REINVITED:
				return sportsman.getName() + " SUCCESSFULLY re-invited to event : " + event.getName() + ".";
			case ALREADY_INVITED:
				return sportsman.getName() + " WAS ALREADY re-invited to event : " + event.getName() + ".";
			default:
				throw new IllegalStateException("Invitation is in unexpected state");
		}
	}


	/*
		Test controller for accept invitation
		{invitationId} - id of Invitation to accept
		@returns - all participants in event which was invitation defined for

		This is not real app method to accept invitation - in real implementation also auth
		of user operation have to be provided.

		All info and ids can be found on /data/get-all
	 */
	@RequestMapping("/invitation/accept/{invitationId}")
	@Transactional
	public String invitationAccept(@PathVariable(value = "invitationId") String invitationId) {

		Long invitationIdLong = Long.valueOf(invitationId);

		Invitation invitation = invitationService.findById(invitationIdLong);
		if (invitation == null) {
			return "Invitation not found";
		}

		Invitation invitationResponse = invitationService.accept(invitation);

		//response
		StringBuilder sb = new StringBuilder("<h3>Event participants:</h3>");
		invitationResponse.getEvent().getParticipants().forEach(participant ->
				sb.append("</p>Id: ").append(participant.getId())
						.append(", Name: ").append(participant.getName())
						.append(" ").append(participant.getSurname()));

		return sb.toString();
	}

	@RequestMapping("/data/get-all")
	public String getAllData() {
		StringBuilder sb = new StringBuilder("<h2>Data:</h2></p>");

		sb.append("<h3>Sportsmans: </h3>");
		sportsmanService.findAll().forEach(sportsman ->
				sb.append("</p>Id: ").append(sportsman.getId())
						.append(", Name: ").append(sportsman.getName())
						.append(" ").append(sportsman.getSurname()));

		sb.append("</p><h3>Sports: </h3>");
		sportService.findAll().forEach(sport ->
				sb.append("</p>Id: ").append(sport.getId())
						.append(", Name: ").append(sport.getName()));

		sb.append("</p><h3>Events: </h3>");
		eventService.findAll().forEach(event ->
				sb.append("</p>Id: ").append(event.getId())
						.append(", Name: ").append(event.getName())
						.append(", Admin: ").append(event.getAdmin().getName())
						.append(" ").append(event.getAdmin().getSurname()));

		sb.append("</p><h3>Invitations: </h3>");
		invitationService.findAll().forEach(invitation ->
				sb.append("</p>Id: ").append(invitation.getId())
						.append(", Invitee name: ").append(invitation.getInvitee().getName())
						.append(" ").append(invitation.getInvitee().getSurname())
						.append(", State: ").append(invitation.getState().name()));

		return sb.toString();
	}

	@RequestMapping("/sportsman/get-all")
	public List<String> getAllSportsmansName() {
		return exampleService.getAllNames();
	}

	@RequestMapping("/sportsman/example-add")
	public String addSportsmantest(@RequestParam String value) {

		Sportsman sportsman = new Sportsman();
		sportsman.setName(value);
		sportsman.setSurname("example");
		sportsman.setEmail("example");
		sportsman.setPassword("example");
		sportsman.setBirthDate(Calendar.getInstance());

		exampleService.addSportsman(sportsman);
		return "ADDED: " + value;
	}
}
