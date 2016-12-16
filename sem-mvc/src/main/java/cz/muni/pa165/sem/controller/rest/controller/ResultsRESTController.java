package cz.muni.pa165.sem.controller.rest.controller;

import cz.muni.pa165.sem.controller.rest.exception.*;
import cz.muni.pa165.sem.dto.ResultCreateDTO;
import cz.muni.pa165.sem.dto.ResultDTO;
import cz.muni.pa165.sem.dto.ResultUpdateDTO;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.util.REST_URI;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Veronika Aksamitova
 */
@RestController
@RequestMapping(REST_URI.ROOT_RESULTS_URI)
public class ResultsRESTController {

    private static Logger logger = LoggerFactory.getLogger(ResultsRESTController.class);

    @Inject
    private ResultFacade resultFacade;

    /**
     * Find all results
     * CURL example in README.md
     *
     * @return requested objects
     * @throws ResourceNotFoundException in case requested objects not found
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResultDTO> getAllResults(){
        logger.debug("Fetching results with");
        try {
            return resultFacade.findAll();
        } catch (Exception e) {
            logger.error("Get all results error", e);
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
    public ResultDTO getResult(@PathVariable("id") long id){

        logger.debug("Fetching result with id " + id);
        try {
            return resultFacade.findById(id);
        } catch (Exception e){
            logger.error("Event with id " + id + " not found", e);
            throw new ResourceNotFoundException(e);
        }
    }

    /**
     * Create new result
     * CURL example in README.md
     *
     * @param result ResultCreateDTO object
     * @param bindingResult resultUpdateDTO values validator
     * @return created result
     * @throws InvalidResourceException in case of validation error
     * @throws ResourceNotModifiedException in case of update failure
     * @throws ResourceNotFoundException in case of updated object not found
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO createResult(@Valid @RequestBody ResultCreateDTO result, BindingResult bindingResult){
        logger.debug("Creating Result " + result);
        if (bindingResult.hasErrors()) {
            throw new InvalidResourceException();
        }
        try {
            return resultFacade.create(result);
        } catch (Exception e) {
            logger.error("Result " + result + " cannot be created.",e);
            throw new ExistingResourceException(e);
        }
    }

    /**
     * Update already existing result
     * CURL example in README.md
     *
     * @param result resultUpdateDTO object we want to update
     * @param bindingResult object fields values validator
     * @return updated result
     * @throws InvalidResourceException in case of validation error
     * @throws ResourceNotModifiedException in case of update failure
     * @throws ResourceNotFoundException in case of updated object not found
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO updateResult(@Valid @RequestBody ResultUpdateDTO result, BindingResult bindingResult){
        logger.debug("Updating Result " + result);
        if (bindingResult.hasErrors()) {
            throw new InvalidResourceException();
        }
        try {
            resultFacade.update(result);
        } catch (Exception e) {
            logger.error("Result update fail",e);
            throw new ResourceNotModifiedException(e);
        }
        try {
            return resultFacade.findById(result.getId());
        } catch (Exception e) {
            logger.error("Find result after update fail",e);
            throw new ResourceNotFoundException(e);
        }
    }

    /**
     * Delete result by id
     * CURL example in README.md
     *
     * @param id of object to delete
     * @throws ResourceDeleteException in case of delete failure
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE
    )
    public void deleteResult(@PathVariable("id") Long id){
        logger.debug("Fetching & deleting Result with id " + id);
        try {
            resultFacade.delete(id);
        } catch (Exception e) {
            logger.error("Deleting result with id " + id + "fail", e);
            throw new ResourceDeleteException(e);
        }
    }


}

