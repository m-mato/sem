package cz.muni.pa165.sem.controller.rest.controller;

import cz.muni.pa165.sem.controller.rest.exception.*;
import cz.muni.pa165.sem.dto.SportCreateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportUpdateDTO;
import cz.muni.pa165.sem.facade.SportFacade;
import cz.muni.pa165.sem.util.REST_URI;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Veronika Aksamitova
 */
@RestController
@RequestMapping(REST_URI.ROOT_SPORTS_URI)
public class SportsRESTController {

    private static Logger logger = LoggerFactory.getLogger(SportsRESTController.class);

    @Inject
    private SportFacade sportFacade;

    /**
     * Find all sports
     * CURL example in README.md
     *
     * @return requested objects
     * @throws ResourceNotFoundException in case requested objects not found
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SportDTO> getAllSports(){
        logger.debug("Fetching all sports");
        try {
            return sportFacade.getAllSports();
        } catch (Exception e) {
            logger.error("Get all sports error", e);
            throw new ResourceNotFoundException(e);
        }
    }

    /**
     * Find object by id
     * CURL example in README.md
     *
     * @param id of object we want to find
     * @return requested object
     * @throws ResourceNotFoundException in case requested object not found
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SportDTO getSport(@PathVariable("id") Long id){
        logger.debug("Fetching Sport with id " + id);
        try {
            return sportFacade.getSportById(id);
        } catch (Exception e){
            logger.error("Sport with id " + id + " not found", e);
            throw new ResourceNotFoundException(e);
        }
    }

    /**
     * Create new sport
     * CURL example in README.md
     *
     * @param sport SportCreateDTO object
     * @param bindingResult sportUpdateDTO values validator
     * @return created sport
     * @throws InvalidResourceException in case of validation error
     * @throws ResourceNotModifiedException in case of update failure
     * @throws ResourceNotFoundException in case of updated object not found
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SportDTO createSport(@Valid @RequestBody SportCreateDTO sport, BindingResult bindingResult){
        logger.debug("Creating Sport " + sport);
        if (bindingResult.hasErrors()) {
            throw new InvalidResourceException();
        }
        try {
            return sportFacade.create(sport);
        } catch (Exception e) {
            logger.error("Sport " + sport + " cannot be created.",e);
            throw new ExistingResourceException(e);
        }
    }

    /**
     * Update already existing sport
     * CURL example in README.md
     *
     * @param sport SportUpdateDTO object we want to update
     * @param bindingResult object fields values validator
     * @return updated sport
     * @throws InvalidResourceException in case of validation error
     * @throws ResourceNotModifiedException in case of update failure
     * @throws ResourceNotFoundException in case of updated object not found
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SportDTO updateSport(@Valid @RequestBody SportUpdateDTO sport, BindingResult bindingResult){
        logger.debug("Updating Sport " + sport);
        if (bindingResult.hasErrors()) {
            throw new InvalidResourceException();
        }
        try {
            sportFacade.update(sport);
        } catch (Exception e) {
            logger.error("Sport update fail",e);
            throw new ResourceNotModifiedException(e);
        }
        try {
            return sportFacade.getSportById(sport.getId());
        } catch (Exception e) {
            logger.error("Find sport after update fail",e);
            throw new ResourceNotFoundException(e);
        }
    }

    /**
     * Delete sport by id
     * CURL example in README.md
     *
     * @param id of object to delete
     * @throws ResourceDeleteException in case of delete failure
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSport(@PathVariable("id") long id){
        logger.debug("Deleting Sport with id " + id);
        try {
            sportFacade.delete(id);
        } catch (Exception e) {
            logger.error("Deleting sport with id " + id + "fail", e);
            throw new ResourceDeleteException(e);
        }
    }

}

