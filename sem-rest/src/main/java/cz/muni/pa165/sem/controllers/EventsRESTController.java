package cz.muni.pa165.sem.controllers;

import cz.muni.pa165.sem.dto.EventCreateDTO;
import cz.muni.pa165.sem.dto.EventDTO;
import cz.muni.pa165.sem.dto.EventUpdateDTO;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.utils.REST_URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author Kamil Triscik.
 */
@RestController
@RequestMapping(REST_URI.ROOT_EVENTS_URI)
public class EventsRESTController {

    private static Logger logger = LoggerFactory.getLogger(EventsRESTController.class);

    @Autowired
    private EventFacade eventFacade;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<EventDTO>> getAllEvents(
            @RequestParam(value="name", required = false) String name) {

        logger.debug("Fetching event with name " + name);

        Collection<EventDTO> events = eventFacade.findAll();

        if(name != null) {
            events.retainAll(eventFacade.findByName(name));
        }

        if(events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> getEvent(@PathVariable("id") long id) {

        logger.debug("Fetching Event with id " + id);
        EventDTO event = eventFacade.findById(id);
        if (event == null) {
            logger.warn("Event with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createEvent(@Valid @RequestBody EventCreateDTO event, BindingResult bindingResult) {

        logger.debug("Creating Event " + event);

        if (bindingResult.hasErrors()) {
            // TODO: 12/5/16 reaction on validation error
        }

        EventDTO result = new EventDTO();
        try {
            result = eventFacade.create(event);
        } catch (Exception ex) {
            logger.warn("Event " + event + " cannot be created. Reason: " + ex);
        }

        // TODO: 12/5/16 currently only return new object in jsob
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventUpdateDTO eventUpdateDTO, BindingResult bindingResult) {

        logger.debug("Updating Event " + eventUpdateDTO);

        if (bindingResult.hasErrors()) {
            // TODO: 12/5/16 reaction on validation error
        }

        eventFacade.update(eventUpdateDTO);
        EventDTO result = eventFacade.findById(eventUpdateDTO.getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable("id") long id) {

        logger.debug("Fetching & deleting Event with id " + id);

        EventDTO event = eventFacade.findById(id);
        if (event == null) {
            logger.warn("Unable to delete. Event with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}