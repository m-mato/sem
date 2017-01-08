package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.editor.CalendarEditor;
import cz.muni.pa165.sem.editor.SportEditor;
import cz.muni.pa165.sem.editor.SportsmanEditor;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.facade.InvitationFacade;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.facade.SportFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.util.InviteRequest;
import cz.muni.pa165.sem.utils.PerformanceUnits;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kamil Triscik
 */
@Controller
@RequestMapping("/events")
public class EventController extends BaseController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    private SportFacade sportFacade;

    @Autowired
    private SportsmanFacade sportsmanFacade;

    @Autowired
    private ResultFacade resultFacade;

    @Inject
    private InvitationFacade invitationFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    @ModelAttribute("sports")
    public List<SportDTO> getSports() {
        return sportFacade.getAllSports();
    }

    @ModelAttribute("sportsmans")
    public List<SportsmanDTO> getSportsmans() {
        return sportsmanFacade.getAll();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Calendar.class, new CalendarEditor("yyyy/MM/dd"));
        binder.registerCustomEditor(SportDTO.class, new SportEditor(sportFacade));
        binder.registerCustomEditor(SportsmanDTO.class, new SportsmanEditor(sportsmanFacade));
    }

    @RequestMapping
    public String renderList(@RequestParam(value = "search", required = false) String search, Model model) {
        List<EventDTO> allEvents = eventFacade.findAll();

        if (search != null && !search.isEmpty()) {
            search = search.toLowerCase();
            List<EventDTO> foundEvents = new ArrayList<>();
            for (EventDTO event : allEvents) {
                if (event.getName().toLowerCase().contains(search)
                        || event.getSport().getName().toLowerCase().contains(search)
                        || event.getCity().toLowerCase().contains(search)
                        || event.getDescription().toLowerCase().contains(search)
                        || event.getAdmin().getSurname().toLowerCase().contains(search)
                        || event.getAdmin().getName().toLowerCase().contains(search)) {
                    foundEvents.add(event);
                }
            }
            model.addAttribute("events", foundEvents);
            model.addAttribute("search", search);
        }
        else {
            model.addAttribute("events", allEvents);
        }
        return "event.list";
    }

    @RequestMapping("/{id}")
    public Object renderDetail(@PathVariable("id") Long id, Model model, Authentication authentication) {
        EventDTO eventDTO = eventFacade.findById(id);
        if (eventDTO == null) {
            return redirect("/events");
        }
        SportsmanDTO sportsman = sportsmanFacade.getByEmail(authentication.getName());
        model.addAttribute("event", eventDTO);

        List<ResultDTO> results = new ArrayList<>();
        List<ResultDTO> allResults = resultFacade.findByEvent(eventDTO);
        for (ResultDTO result : allResults) {
            if (result.getPerformance() >= 0 && result.getPosition() >= 0) {
                results.add(result);
            }
        }
        model.addAttribute("results", results);

        if (resultFacade.findByEvent(eventDTO).stream().filter(
                resultDTO -> resultDTO.getSportsman().getEmail().equals(sportsman.getEmail())
        ).collect(Collectors.toList()).isEmpty()) {
            model.addAttribute("isParticipant", false); //check if is participant
        } else {
            model.addAttribute("isParticipant", true); //check if is participant
        }
        model.addAttribute("invitation", invitationFacade.findByEventAndInvitee(eventDTO, sportsman));
        return "event.detail";
    }

    @RequestMapping("/create")
    public String renderCreate(Authentication authentication, Model model) {
        EventCreateDTO eventCreateDTO = new EventCreateDTO();
        SportsmanDTO sportsmanDTO = sportsmanFacade.getByEmail(authentication.getName());
        eventCreateDTO.setAdmin(sportsmanDTO);
        model.addAttribute("event", eventCreateDTO);
        return "event.create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object processCreate(@ModelAttribute("event") @Valid EventCreateDTO eventCreateDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", true);
            return "event.create";
        }
        EventDTO eventDTO = eventFacade.create(eventCreateDTO);
        return redirect("/events/" + eventDTO.getId() + "?create");
    }

    @RequestMapping("/{id}/update")
    public Object renderUpdate(@PathVariable("id") Long id, Model model) {
        EventDTO eventDTO = eventFacade.findById(id);
        if (eventDTO == null) {
            return redirect("/events");
        }
        model.addAttribute("event", beanMappingService.mapTo(eventDTO, EventUpdateDTO.class));
        return "event.update";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public Object processUpdate(@Valid @ModelAttribute("event") EventUpdateDTO eventUpdateDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", true);
            return "event.update";
        }
        eventFacade.update(eventUpdateDTO);
        return redirect("/events/" + eventUpdateDTO.getId() + "?update");
    }

    @RequestMapping("/{id}/delete")
    public Object renderDelete(@PathVariable("id") Long id) {
        EventDTO eventDTO = eventFacade.findById(id);
        if (eventDTO != null) {
            eventFacade.delete(eventDTO.getId());
        }
        return redirect("/events?delete");
    }

    @RequestMapping( value = "/{id}/unenroll", method = RequestMethod.GET)
    public Object unenroll(@PathVariable long id, Authentication authentication, Model model) {
        logger.info("renderEvents");
        SportsmanDTO participant = sportsmanFacade.getByEmail(authentication.getName());
        EventDTO event = eventFacade.findById(id);
        logger.info("Unenrolling sportman(" + participant.getName() + " " + participant.getSurname() + ") from event " + event.getName());
        resultFacade.delete(
                //find result we want to delete
                resultFacade.findBySportsmanAndEvent(
                        participant, //find sportsman  me
                        event).getId());  //find event
        model.addAttribute("message", "unenrolled");
        return redirect("/events/" + id + "?unenrolled");
    }

    @RequestMapping( value = "/{id}/enroll", method = RequestMethod.GET)
    public Object enroll(@PathVariable long id, Authentication authentication, Model model) {
        logger.info("renderEvents");
        SportsmanDTO participant = sportsmanFacade.getByEmail(authentication.getName());
        EventDTO event = eventFacade.findById(id);
        logger.info("Enrolling sportman(" + participant.getName() + " " + participant.getSurname() + ") from event " + event.getName());
        ResultCreateDTO  result = new ResultCreateDTO();
        result.setPerformanceUnit(PerformanceUnits.POINT);
        result.setEvent(event);
        result.setSportsman(participant);
        result.setPosition(new Integer(-1));
        result.setPerformance(new Double(-1));
        result.setNote("");
        resultFacade.create(result);

        List<SportsmanDTO> part = event.getParticipants();
        part.add(participant);
        event.setParticipants(part);
        eventFacade.update(beanMappingService.mapTo(event, EventUpdateDTO.class));

        InvitationDTO invitation = invitationFacade.findByEventAndInvitee(event, participant);
        if (invitation != null) {
            invitationFacade.simpleAccept(beanMappingService.mapTo(invitation, InvitationUpdateDTO.class));
        }

        model.addAttribute("enrollProcess", "enrolled");
        return redirect("/events/" + id + "?enrolled");
    }

    @RequestMapping(value = "/autocomplet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SportsmanDTO>> autoComplet(@RequestParam("pattern") String pattern, @RequestParam("event_id") Long event_id, Model model) {
        return new ResponseEntity<>(sportsmanFacade.findBySubstring(pattern, event_id),  HttpStatus.OK);
    }

    @RequestMapping( value = "/invite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity invite(@RequestBody InviteRequest request) {
        logger.debug("Invitation for event with id=" + request.getEvent_id() +" and for user with email " + request.getInputEmail());
        invitationFacade.invite(request.getEvent_id(), sportsmanFacade.getByEmail(request.getInputEmail()).getId());
        return new ResponseEntity("{}", HttpStatus.OK);
    }

}
