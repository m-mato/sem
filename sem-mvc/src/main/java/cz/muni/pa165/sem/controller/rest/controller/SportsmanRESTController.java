package cz.muni.pa165.sem.controller.rest.controller;

import cz.muni.pa165.sem.controller.rest.exception.*;
import cz.muni.pa165.sem.dto.SportsmanCreateDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.dto.SportsmanUpdateDTO;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import cz.muni.pa165.sem.util.REST_URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Kamil Triscik.
 */
@RestController
@RequestMapping(REST_URI.ROOT_SPORTSMANS_URI)
public class SportsmanRESTController {

    private static Logger logger = LoggerFactory.getLogger(SportsmanRESTController.class);

    @Inject
    private SportsmanFacade sportsmanFacade;

    /**
     * Find all sportsmans (by name - optional)
     * CURL example in README.md
     *
     * @param name optional param
     * @return requested objects
     * @throws ResourceNotFoundException in case requested objects not found
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SportsmanDTO> getAllSportsmans(@RequestParam(value="name", required = false) String name) {
        logger.debug("Fetching sportsman with");
        try {
            List<SportsmanDTO> sportsmanDTOs = sportsmanFacade.getAll();
            if(name != null) {
                sportsmanDTOs.retainAll(sportsmanFacade.getByName(name));
            }
            return sportsmanDTOs;
        } catch (Exception e) {
            logger.error("Get all sportsmans error", e);
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
    public final SportsmanDTO getSportsman(@PathVariable("id") long id) {
        logger.debug("Fetching Sportsman with id " + id);
        try {
            return sportsmanFacade.getById(id);
        } catch (Exception e){
            logger.error("Sportsman with id " + id + " not found", e);
            throw new ResourceNotFoundException(e);
        }
    }

    /**
     * Create new sportsman
     * CURL example in README.md
     *
     * @param sportsman SportsmanCreateDTO object
     * @param bindingResult sportsmanUpdateDTO values validator
     * @return created sportsman
     * @throws InvalidResourceException in case of validation error
     * @throws ResourceNotModifiedException in case of update failure
     * @throws ResourceNotFoundException in case of updated object not found
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SportsmanDTO createSportsman(@Valid @RequestBody SportsmanCreateDTO sportsman, BindingResult bindingResult) {
        logger.debug("Creating Sportsman " + sportsman);
        if (bindingResult.hasErrors()) {
            throw new InvalidResourceException();
        }
        try {
           return sportsmanFacade.create(sportsman);
        } catch (Exception e) {
            logger.error("Sportsman " + sportsman + " cannot be created.",e);
            throw new ExistingResourceException(e);
        }
    }

    /**
     * Update already existing sportsman
     * CURL example in README.md
     *
     * @param sportsmanUpdateDTO object we want to update
     * @param bindingResult object fields values validator
     * @return updated sportsman
     * @throws InvalidResourceException in case of validation error
     * @throws ResourceNotModifiedException in case of update failure
     * @throws ResourceNotFoundException in case of updated object not found
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SportsmanDTO updateSportsman(@Valid @RequestBody SportsmanUpdateDTO sportsmanUpdateDTO, BindingResult bindingResult) {
        logger.debug("Updating Sportsman " + sportsmanUpdateDTO);
        if (bindingResult.hasErrors()) {
            throw new InvalidResourceException();
        }
        try {
            sportsmanFacade.update(sportsmanUpdateDTO);
        } catch (Exception e) {
            logger.error("Sportsman update fail",e);
            throw new ResourceNotModifiedException(e);
        }
        try {
            return sportsmanFacade.getById(sportsmanUpdateDTO.getId());
        } catch (Exception e) {
            logger.error("Find sportsman after update fail",e);
            throw new ResourceNotFoundException(e);
        }
    }

    /**
     * Delete sportsman by id
     * CURL example in README.md
     *
     * @param id of object to delete
     * @throws ResourceDeleteException in case of delete failure
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteSportsman(@PathVariable("id") long id) {
        logger.debug("Deleting Sportsman with id " + id);
        try {
            sportsmanFacade.delete(id);
        } catch (Exception e) {
            logger.error("Deleting Sportsman with id " + id + "fail", e);
            throw new ResourceDeleteException(e);
        }
    }

}