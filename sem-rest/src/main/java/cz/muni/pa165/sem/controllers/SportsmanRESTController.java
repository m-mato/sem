package cz.muni.pa165.sem.controllers;

import cz.muni.pa165.sem.facade.SportsmanFacade;
import cz.muni.pa165.sem.utils.REST_URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kamil Triscik.
 */
@RestController
@RequestMapping(REST_URI.ROOT_SPORTSMANS_URI)
public class SportsmanRESTController {

    private static Logger logger = LoggerFactory.getLogger(SportsmanRESTController.class);

    @Autowired
    private SportsmanFacade sportsmanFacade;

}
