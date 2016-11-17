package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.entity.Event;
import cz.muni.pa165.sem.entity.Sport;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.service.EventService;
import cz.muni.pa165.sem.service.ExampleService;
import cz.muni.pa165.sem.service.InvitationService;
import cz.muni.pa165.sem.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
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
	private EventService eventService;

	@Autowired
	private SportService sportService;

	@RequestMapping("/invite")
	@Transactional
	public String invite(@RequestParam String value) {

		Sportsman admin = new Sportsman();
		admin.setName("Dusan");
		admin.setSurname("Fetak");
		admin.setEmail("fetak.dusan@example.com");
		admin.setPassword("examplePassword");
		admin.setBirthDate(Calendar.getInstance());
		exampleService.addSportsman(admin);

		Sportsman sportsman = new Sportsman();
		sportsman.setName(value);
		sportsman.setSurname("Lakatos");
		sportsman.setEmail("mato.majdis@gmail.com");
		sportsman.setPassword("examplePassword");
		sportsman.setBirthDate(Calendar.getInstance());
		exampleService.addSportsman(sportsman);

		Sport sport = new Sport();
		sport.setName("football");
		sport.setDescription("test desc");
		sportService.create(sport);

		Event event = new Event();
		event.setAddress("Brno - Botanicka");
		event.setAdmin(admin);
		event.setCapacity(666);
		event.setCity("Brno");
		event.setDate(Calendar.getInstance());
		event.setDescription("test event");
		event.setSport(sport);
		event.setName("test event");
		event.setParticipants(new HashSet<>());
		eventService.create(event);

		invitationService.invite(event, sportsman);

		//invitationService.invite(event, sportsman);

		return "ADDED";
	}

	@RequestMapping("/sportsman/example-add")
	public String addSportsman(@RequestParam String value) {

		Sportsman sportsman = new Sportsman();
		sportsman.setName(value);
		sportsman.setSurname("example");
		sportsman.setEmail("example");
		sportsman.setPassword("example");
		sportsman.setBirthDate(Calendar.getInstance());

		exampleService.addSportsman(sportsman);
		return "ADDED: " + value;
	}

	@RequestMapping("/sportsman/get-all")
	public List<String> getAllNames() {
		return exampleService.getAllNames();
	}
}
