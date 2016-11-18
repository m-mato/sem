package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dao.InvitationDAO;
import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

/**
 * @author Matej Majdis
 */
@RestController
@RequestMapping("/test")
public class ExampleController {

	@Autowired
	private ExampleService exampleService;

	@Autowired
	private InvitationService invitationService;

	@Autowired
	private InvitationDAO invitationDAO;

	@Autowired
	private EventService eventService;

	@Autowired
	private SportService sportService;

	@Autowired
	private SportsmanService sportsmanService;

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
		if(invitation == null) {
			return sportsman.getName() + "is already member of event : " + event.getName();
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

	@RequestMapping("/sportsman/get-all")
	public List<String> getAllSportsmansName() {
		return exampleService.getAllNames();
	}

	@RequestMapping("/data/get-all")
	public String getAllData() {
		StringBuilder sb = new StringBuilder();
		sportsmanService.findAll().forEach(sb::append);
		sb.append("\n\n");
		sportService.findAll().forEach(sb::append);
		sb.append("\n\n");
		eventService.findAll().forEach(sb::append);
		sb.append("\n\n");
		invitationDAO.findAll().forEach(sb::append);

		return sb.toString();
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
