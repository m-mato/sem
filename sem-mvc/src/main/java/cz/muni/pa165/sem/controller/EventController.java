package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.facade.SportFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.Valid;

/**
 * @author Kamil Triscik
 */
@Controller
public class EventController extends BaseController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(EventController.class);


    @Autowired
    private ServletContext servletContext;

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    private SportFacade sportFacade;

    @Autowired
    private SportsmanFacade sportsmanFacade;

    @Autowired
    private ResultFacade resultFacade;

    /*@RequestMapping("/events")
    public String renderEvents(Authentication authentication, Model model) {
        logger.info("renderEvents");
        SportsmanDTO participant = sportsmanFacade.getByEmail(authentication.getName());
        List<EventDTO> events = eventFacade.findAll();//findByParticipant(1L);//participant.getId());
        logger.info("events" + events.size());
        model.addAttribute("events", events);
        if (!events.isEmpty()) {
            EventDTO event = events.get(0);
            model.addAttribute("event", events.get(0));
            model.addAttribute("result", resultFacade.findBySportsmanAndEvent(participant, event));
        }
        return "event-participant";
    }*/

    //ALL EVENTS VIEW ....not just for user ...but need to be authenticated
    @RequestMapping("/events")
    public String renderEvents(Authentication authentication, Model model) {
        logger.info("Rendering events");
        List<EventDTO> events = eventFacade.findAll();
        logger.info("events" + events.size());
        model.addAttribute("events", events);
        return "events";
    }


        @RequestMapping("/events/{eventId}")
    public String renderEvent(@PathVariable long eventId, Model model) {
        model.addAttribute("event", eventFacade.findById(eventId));
        model.addAttribute("result", resultFacade.findById(1L));// TODO: 14-Dec-16 from event

        return "event-participant";
    }

    @RequestMapping( value = "/events/{id}/unenroll", method = RequestMethod.GET)
    public String unenroll(@PathVariable long id, Authentication authentication) {
        logger.info("renderEvents");
        SportsmanDTO participant = sportsmanFacade.getByEmail(authentication.getName());
        EventDTO event = eventFacade.findById(id);
        logger.info("Unenrolling sportman(" + participant.getName() + " " + participant.getSurname() + ") from event " + event.getName());
        resultFacade.delete(
                //find result we want to delete
                resultFacade.findBySportsmanAndEvent(
                        participant, //find sportsman  me
                        event).getId());  //find event
        // TODO: 13-Dec-16 redirect where you want
        return "events-participant";
    }

    @RequestMapping(value = "/events/autocomplet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SportsmanDTO>> autoComplet(@RequestParam("pattern") String pattern, Model model) {
        return new ResponseEntity<>(sportsmanFacade.findBySubstring(pattern),  HttpStatus.OK);
    }

    @RequestMapping(value = "/create-event", method=RequestMethod.GET)
    public String createEvent(Model model){
        logger.debug("Starting to create event");
        model.addAttribute("sports", sportFacade.getAllSports());
        model.addAttribute("event", new EventCreateDTO());
        return "create-event";
    }



    @RequestMapping(value = "/event/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("event") EventCreateDTO event, BindingResult bindingResult,
                         HttpServletResponse resp) throws IOException {
        logger.debug("Creating event: ", event.toString());
        EventDTO createdEvent;

        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            SportsmanDTO sportsman = sportsmanFacade.getByEmail(email);
            event.setAdmin(sportsman);
            if (bindingResult.hasErrors()) {
                logger.debug("Creation of event: {0} was not successuful", event.toString());
                return "create-event";
            }
            createdEvent = eventFacade.create(event);
        }
        catch(Exception ex){
            return "create-event";
        }
        resp.sendRedirect(servletContext.getContextPath() + "/events/"+ createdEvent.getId());
       // redirectAttributes.addFlashAttribute("alert_success", "Event was created");
        return null;
    }



}

