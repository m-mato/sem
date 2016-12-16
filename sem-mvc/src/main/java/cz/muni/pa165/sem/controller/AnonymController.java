package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.EventDTO;
import cz.muni.pa165.sem.facade.EventFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matej Majdis
 */
@Controller
public class AnonymController extends BaseController {

	@Autowired
	private EventFacade eventFacade;

	@RequestMapping("/find-event")
	public String renderFindEvent(@RequestParam(value = "search", required = false) String searchValue, Model model) {

		if (searchValue != null && !searchValue.isEmpty()) {

			List<EventDTO> events = new ArrayList<>();
			List<EventDTO> allEvents = eventFacade.findAll();
			for (EventDTO event : allEvents) {
				if (event.getName().toLowerCase().contains(searchValue.toLowerCase())) {
					events.add(event);
				}
			}

			model.addAttribute("events", events);
			model.addAttribute("search", searchValue);
		}

		return "find-event";
	}
}
