package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.entity.Invitation;
import cz.muni.pa165.sem.facade.InvitationFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kamil Triscik
 */
@Controller
public class InvitationController extends BaseController {

    Logger logger = LoggerFactory.getLogger(InvitationController.class);

    @Autowired
    InvitationFacade invitationFacade;

    @Autowired
    SportsmanFacade sportsmanFacade;

    @RequestMapping(value = "/invite/{id}", method = RequestMethod.POST)
    public void invite(@PathVariable long id, @RequestParam(value = "inviteSelect", required = true) String email) {
        logger.debug("Invitation fot event with id=" + email +"= and for user with email ");
        //get an email(parse if necessary)
//        invitationFacade.invite(eventId, sportsmanFacade.getByEmail(email).getId());
    }

    private class InvitationRequest{
        public long getEventId() {
            return eventId;
        }

        public void setEventId(long eventId) {
            this.eventId = eventId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        long eventId;
        String email;
    }

}
