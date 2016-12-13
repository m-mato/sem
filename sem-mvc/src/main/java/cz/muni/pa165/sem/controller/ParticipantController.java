package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.facade.EventFacade;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Kamil Triscik
 */
@Controller
public class ParticipantController extends BaseController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ParticipantController.class);

    @Inject
    private EventFacade eventFacade;

    @RequestMapping("/events")
    public String renderEvents(Model model) {
        logger.info("renderEvents");
        List events = eventFacade.findAll();
        model.addAttribute("events", events);
        model.addAttribute("event", events.get(0));
        return "events-participant";
    }

    @RequestMapping("/events/{eventId}")
    public String renderEvent(@PathVariable long eventId, Model model) {
        model.addAttribute("event", eventFacade.findById(eventId));
        return "event-participant";
    }

}
