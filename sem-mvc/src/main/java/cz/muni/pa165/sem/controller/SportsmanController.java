package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.facade.InvitationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import cz.muni.pa165.sem.dto.ResultDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Veronika Aksamitova
 */
@Controller
public class SportsmanController extends BaseController {

    @Autowired
    EventFacade eventFacade;

    @Autowired
    SportsmanFacade sportsmanFacade;

    @Inject
    InvitationFacade invitationFacade;

    @Autowired
    ResultFacade resultFacade;


    @RequestMapping("/my-account")
    public String myAccount(Model model) {
        String email;
        SportsmanDTO sportsman = new SportsmanDTO();
        List<ResultDTO> results;
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            email = auth.getName();
            sportsman = sportsmanFacade.getByEmail(email);
            results = resultFacade.findBySportsman(sportsman);
        }
        catch(Exception ex){
            return "index";
        }
        model.addAttribute("name", sportsman.getName());
        model.addAttribute("surname", sportsman.getSurname());
        model.addAttribute("email", sportsman.getEmail());
        model.addAttribute("birthdate", sportsman.getBirthDate());
        model.addAttribute("invitations", invitationFacade.findByInvitee(sportsman));
        model.addAttribute("events", sportsman.getEvents());
        model.addAttribute("results", results);
        return "my-account";
    }


    @RequestMapping("/accept/{id}")
    public String accept(@PathVariable Long id) {
        String email;
        SportsmanDTO sportsman = new SportsmanDTO();
        List<ResultDTO> results;
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            email = auth.getName();
            sportsman = sportsmanFacade.getByEmail(email);
            invitationFacade.findByInvitee(sportsman);
                    //Accept invitation with id
        }
        catch(Exception ex){
        }
        return "my-account";
    }

    /**
     * Method to find autocomplete options for invite people feature
     *
     * @param pattern - string to find
     * @param model - request model
     * @return possible options for autocomplete
     */
    @RequestMapping(value = "/events/autocomplet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SportsmanDTO>> autoComplet(@RequestParam("pattern") String pattern, Model model) {
        List<SportsmanDTO> list = sportsmanFacade.getAll();
        List<SportsmanDTO> results = list.stream().filter(
                (sportsman) -> sportsman.getName().contains(pattern)
        || sportsman.getSurname().contains(pattern)
                        || sportsman.getEmail().contains(pattern)).collect(Collectors.toList());
        return new ResponseEntity<>(results,  HttpStatus.OK);
    }

    @RequestMapping("/decline/{id}")
    public String decline(@PathVariable Long id) {
        String email;
        SportsmanDTO sportsman = new SportsmanDTO();
        List<ResultDTO> results;
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            email = auth.getName();
            sportsman = sportsmanFacade.getByEmail(email);
            invitationFacade.findByInvitee(sportsman);
            //Accept invitation with id
        }
        catch(Exception ex){
        }
        return "my-account";
    }


}