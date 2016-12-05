package cz.muni.pa165.sem.rest.controllers;

import cz.muni.pa165.sem.dto.SportCreateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportUpdateDTO;
import cz.muni.pa165.sem.facade.SportFacade;
import cz.muni.pa165.sem.rest.utils.REST_URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.Collection;


/**
 * Created by Veronika Aksamitova on 5. 12. 2016.
 */
@RestController
@RequestMapping(REST_URI.ROOT_SPORTS_URI)
public class SportsRESTController {

    @Autowired
    private SportFacade sportFacade;

    private static Logger logger = LoggerFactory.getLogger(SportsRESTController.class);

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SportDTO>> getAllSports(){

        logger.debug("Fetching all sports");

        Collection<SportDTO> sports = sportFacade.getAllSports();
        if(sports.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sports, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SportDTO> getSport(@PathVariable("id") Long id){

        logger.debug("Fetching Sport with id " + id);
        SportDTO sport = sportFacade.getSportById(id);

        if(sport == null){
            logger.warn("Sport with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sport, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SportDTO> createSport(@Valid @RequestBody SportCreateDTO sport, BindingResult bindingResult){

        logger.debug("Creating Sport " + sport);
        if(bindingResult.hasErrors()){
            //TODO
        }

        SportDTO createdSport = new SportDTO();
        try{
            createdSport = sportFacade.create(sport);
        }
        catch(Exception ex){
            logger.warn("Sport " + sport +" can not be created. Reason: " + ex);
        }

        return new ResponseEntity(createdSport, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SportDTO> updateSport(@Valid @RequestBody SportUpdateDTO sport, BindingResult bindingResult){

        logger.debug("Updating Sport " + sport);

        if(bindingResult.hasErrors()){
            //TODO
        }

        try{
            sportFacade.update(sport);
        }
        catch(Exception ex){
            logger.warn("Sport " + sport + " cannot be updated. Reason: " + ex);
        }
        SportDTO updatedSport = sportFacade.getSportById(sport.getId());
        return new ResponseEntity(updatedSport, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<SportDTO> deleteSport(@PathVariable("id") Long id){
        logger.debug("Fetching & deleting Sport with id " + id);

        SportDTO sport = sportFacade.getSportById(id);

        if(sport == null){
            logger.warn("Unable to delete. Sport with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try{
            sportFacade.delete(id);
        }
        catch(Exception ex){
            logger.warn("Sport " + sport + " cannot be deleted. Reason: " + ex);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

