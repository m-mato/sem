package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

/**
 * @author Matej Majdis
 */
@RestController
@RequestMapping("/test")
public class ExampleController {

	@Autowired
	private ExampleService exampleService;

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
