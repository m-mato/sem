package cz.muni.pa165.sem.controller.rest;

import cz.muni.pa165.sem.dto.ResultCreateDTO;
import cz.muni.pa165.sem.dto.ResultDTO;
import cz.muni.pa165.sem.dto.ResultUpdateDTO;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.util.REST_URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by Veronika Aksamitova on 5. 12. 2016.
 */
@RestController
@RequestMapping(REST_URI.ROOT_RESULTS_URI)
public class ResultsRESTController {

    @Autowired
    private ResultFacade resultFacade;

    private static Logger logger = LoggerFactory.getLogger(ResultsRESTController.class);

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ResultDTO>> getAllResults(){

        Collection<ResultDTO> results = resultFacade.findAll();

        if(results.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> getResult(@PathVariable("id") long id){

        logger.debug("Fetching result with id " + id);
        ResultDTO result = resultFacade.findById(id);

        if(result == null){
            logger.warn("Result with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDTO> createResult(@Valid @RequestBody ResultCreateDTO result, BindingResult bindingResult){

        logger.debug("Creating Result " + result);

        if (bindingResult.hasErrors()) {
            // TODO: ...validation errors ...copied from EventsRESTController
        }

        ResultDTO createdResult = new ResultDTO();
        try{
            resultFacade.create(result);
        }
        catch(Exception ex){
            logger.warn("Result " + result + " cannot be created. Reason: " + ex);
        }

        return new ResponseEntity(createdResult, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateResult(@Valid @RequestBody ResultUpdateDTO result, BindingResult bindingResult){

        logger.debug("Updating Result " + result);

        if(bindingResult.hasErrors()){
            //TODO
        }

        try{
            resultFacade.update(result);
        }
        catch(Exception ex){
            logger.warn("Result " + result + " cannot be updated. Reason: " + ex);
        }
        ResultDTO updatedResult = resultFacade.findById(result.getId());
        return new ResponseEntity(updatedResult, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<ResultDTO> deleteResult(@PathVariable("id") Long id){
        logger.debug("Fetching & deleting Result with id " + id);

        ResultDTO result = resultFacade.findById(id);

        if(result == null){
            logger.warn("Unable to delete. Result with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try{
            resultFacade.delete(id);
        }
        catch(Exception ex){
            logger.warn("Result " + result + " cannot be deleted. Reason: " + ex);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

