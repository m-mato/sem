package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.facade.InvitationFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kamil Triscik
 */
@Controller
public class InvitationController extends BaseController {

    @Autowired
    InvitationFacade invitationFacade;

    @Autowired
    SportsmanFacade sportsmanFacade;

    @RequestMapping(value = "/events/{id}/invite", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity invite(@PathVariable Long id, @RequestParam("email") String email) {
        invitationFacade.invite(id, sportsmanFacade.getByEmail(email).getId());
        return new ResponseEntity(HttpStatus.OK);
    }

}
