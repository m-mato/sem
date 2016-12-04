package cz.muni.pa165.sem.controllers;

import cz.muni.pa165.sem.utils.REST_URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kamil Triscik.
 */
public class RootRESTController {

    final static Logger logger = LoggerFactory.getLogger(RootRESTController.class);

    @RestController
    public class ParentController {

        @RequestMapping(
                value = REST_URI.ROOT_SEM_URI,
                method = RequestMethod.GET,
                produces = MediaType.APPLICATION_JSON_VALUE)
        public final Map<String, String> getResources() {
            Map<String,String> resourcesMap = new HashMap<>();

            resourcesMap.put("sportsmans_uri", REST_URI.ROOT_SPORTSMANS_URI);
            resourcesMap.put("events_uri", REST_URI.ROOT_EVENTS_URI);
            resourcesMap.put("sports_uri", REST_URI.ROOT_SPORTS_URI);
            resourcesMap.put("results_uri", REST_URI.ROOT_RESULTS_URI);

            return Collections.unmodifiableMap(resourcesMap);
        }

    }

}
