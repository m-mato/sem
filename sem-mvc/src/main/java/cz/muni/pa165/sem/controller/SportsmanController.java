package cz.muni.pa165.sem.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Veronika Aksamitova
 */
@Controller
public class SportsmanController extends BaseController {

    @Autowired
    EventFacade eventFacade;

    @Autowired
    SportsmanFacade sportsmanFacade;

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
        model.addAttribute("invitations", sportsman.getInvitations());
        model.addAttribute("events", sportsman.getEvents());
        model.addAttribute("results", results);
        return "my-account";
    }
}