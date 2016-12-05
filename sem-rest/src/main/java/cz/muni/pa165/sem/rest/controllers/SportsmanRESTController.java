package cz.muni.pa165.sem.rest.controllers;

import cz.muni.pa165.sem.dto.SportsmanCreateDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.dto.SportsmanUpdateDTO;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import cz.muni.pa165.sem.rest.utils.REST_URI;
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
public class SportsmanRESTController {

    private static Logger logger = LoggerFactory.getLogger(SportsmanRESTController.class);

    @Autowired
    private SportsmanFacade sportsmanFacade;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SportsmanDTO>> getAllSportsmans(
            @RequestParam(value="name", required = false) String name) {

        logger.debug("Fetching sportsman with name " + name);

        Collection<SportsmanDTO> sportsmanDTOs = sportsmanFacade.getAll();

        if(name != null) {
            sportsmanDTOs.retainAll(sportsmanFacade.getByName(name));
        }

        if(sportsmanDTOs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sportsmanDTOs, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SportsmanDTO> getSportsman(@PathVariable("id") long id) {

        logger.debug("Fetching Sportsman with id " + id);
        SportsmanDTO sportsman = sportsmanFacade.getById(id);
        if (sportsman == null) {
            logger.warn("Sportsman with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sportsman, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSportsman(@Valid @RequestBody SportsmanCreateDTO sportsman, BindingResult bindingResult) {

        logger.debug("Creating Sportsman " + sportsman);

        if (bindingResult.hasErrors()) {
            // TODO: 12/5/16 reaction on validation error
        }

        SportsmanDTO result = new SportsmanDTO();
        try {
            result = sportsmanFacade.create(sportsman);
        } catch (Exception ex) {
            logger.warn("Sportsman " + sportsman + " cannot be created. Reason: " + ex);
        }

        // TODO: 12/5/16 currently only return new object in json
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SportsmanDTO> updateSportsman(@Valid @RequestBody SportsmanUpdateDTO sportsmanUpdateDTO, BindingResult bindingResult) {

        logger.debug("Updating Sportsman " + sportsmanUpdateDTO);

        if (bindingResult.hasErrors()) {
            // TODO: 12/5/16 reaction on validation error
        }

        sportsmanFacade.update(sportsmanUpdateDTO);
        SportsmanDTO result = sportsmanFacade.getById(sportsmanUpdateDTO.getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<SportsmanDTO> deleteSportsman(@PathVariable("id") long id) {

        logger.debug("Fetching & deleting Sportsman with id " + id);

        SportsmanDTO sportsman = sportsmanFacade.getById(id);
        if (sportsman == null) {
            logger.warn("Unable to delete. Sportsman with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        sportsmanFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}