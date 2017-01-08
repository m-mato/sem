package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.InvitationUpdateDTO;
import cz.muni.pa165.sem.facade.InvitationFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import org.springframework.security.core.Authentication;
import cz.muni.pa165.sem.dto.ResultDTO;
import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.dto.InvitationDTO;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veronika Aksamitova
 */
@Controller
public class SportsmanController extends BaseController {

    @Autowired
    BeanMappingService beanMappingService;

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
        List<ResultDTO> results = new ArrayList<>();
        try {
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
        model.addAttribute("events", eventFacade.findByParticipant(sportsman.getId()));
        model.addAttribute("results", results);
        return "user.detail";
    }


    @RequestMapping("/accept/{id}")
    public Object accept(Authentication authentication, @PathVariable Long id) {
        InvitationDTO  invitation;
        try{
            invitation = invitationFacade.findById(id);
            if(!authentication.getName().equals(invitation.getInvitee().getEmail())) {
                return "error.403";
            }
            invitationFacade.accept(beanMappingService.mapTo(invitation, InvitationUpdateDTO.class));
        }
        catch(Exception ex){

            return "error.403";
        }
        return redirect("/events/" + invitation.getEvent().getId() + "?accepted");
    }

    @RequestMapping("/decline/{id}")
    public Object decline(@PathVariable Long id) {
        try{
            InvitationDTO  invitation = invitationFacade.findById(id);
            invitationFacade.decline(beanMappingService.mapTo(invitation, InvitationUpdateDTO.class));
        }
        catch(Exception ex){

            return "error.403";
        }
        return redirect("/my-account?decline");
    }


}
