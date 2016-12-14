package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Kamil Triscik
 */
@Controller
public class ParticipantController extends BaseController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ParticipantController.class);

    @Inject
    private EventFacade eventFacade;

    @Inject
    private SportsmanFacade sportsmanFacade;

    @Inject
    private ResultFacade resultFacade;

    @RequestMapping("/events")
    public String renderEvents(Model model) {
        logger.info("renderEvents");
        List<EventDTO> events = eventFacade.findAll();//todo for specific sportsman
        model.addAttribute("events", events);
        model.addAttribute("event", events.get(0));
        model.addAttribute("result", resultFacade.findById(1L));// TODO: 14-Dec-16 from event
        return "events-participant";
    }

    @RequestMapping("/events/{eventId}")
    public String renderEvent(@PathVariable long eventId, Model model) {
        model.addAttribute("event", eventFacade.findById(eventId));
        model.addAttribute("result", resultFacade.findById(1L));// TODO: 14-Dec-16 from event

        return "event-participant";
    }

    @RequestMapping( value = "/events/{id}/unenroll", method = RequestMethod.GET)
    public String unenroll(@PathVariable long id) {
        SportsmanDTO sportsman = sportsmanFacade.getById(1L);// TODO: 14-Dec-16 authenticated user
        EventDTO event = eventFacade.findById(id);
        logger.info("Unenrolling sportman(" + sportsman.getName() + " " + sportsman.getSurname() + ") from event " + event.getName());
        resultFacade.delete(
                //find result we want to delete
                resultFacade.findBySportsmanAndEvent(
                        sportsman, //find sportsman  me
                        event).getId());  //find event
        // TODO: 13-Dec-16 redirect where you want
        return "events-participant";
    }

    @RequestMapping(value = "/events/autocomplet", method = RequestMethod.POST)
    public Set autoComplet(@RequestParam("pattern") String pattern, Model model) {
        TreeSet sportsmans = new TreeSet();
        sportsmans.addAll(sportsmanFacade.getByName(pattern));
        sportsmans.addAll(sportsmanFacade.getBySurname(pattern));
        model.addAttribute("sportsmans", sportsmans);
        return sportsmans;
    }

    @RequestMapping(value = "/events/{id}/invite")
    public String invite(@PathVariable Long id) {
//        InvitationDTO invitationDTO =
        return null;
    }



}
