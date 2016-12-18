package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.InvitationUpdateDTO;
import cz.muni.pa165.sem.entity.Sportsman;
import cz.muni.pa165.sem.facade.InvitationFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.utils.InvitationState;
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
        return "user.detail";
    }


    @RequestMapping("/accept/{id}")
    public String accept(@PathVariable Long id) {
        String email;
        SportsmanDTO sportsman;
        try{
            /*Sportsman user = (Sportsman)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            email = user.getEmail();
            sportsman = sportsmanFacade.getByEmail(email);
            List<InvitationDTO> invitations =invitationFacade.findByInvitee(sportsman);*
            for(InvitationDTO invitation : invitations){
                if(invitation.getId().equals(id)){
                    invitationFacade.accept(beanMappingService.mapTo(invitation, InvitationUpdateDTO.class));
                }
            }*/
            InvitationDTO  invitation = invitationFacade.findById(id);
            invitationFacade.accept(beanMappingService.mapTo(invitation, InvitationUpdateDTO.class));
        }
        catch(Exception ex){

            return "error.403";
        }
        return "event.list";
    }

    @RequestMapping("/decline/{id}")
    public String decline(@PathVariable Long id) {
        String email;
        SportsmanDTO sportsman = new SportsmanDTO();
        List<ResultDTO> results;
        try{
            /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            email = auth.getName();
            sportsman = sportsmanFacade.getByEmail(email);
            List<InvitationDTO> invitations =invitationFacade.findByInvitee(sportsman);
            for(InvitationDTO invitation : invitations){
                if(invitation.getId().equals(id)){
                    invitationFacade.decline(beanMappingService.mapTo(invitation, InvitationUpdateDTO.class));
                }
            }*/

            InvitationDTO  invitation = invitationFacade.findById(id);
            invitationFacade.decline(beanMappingService.mapTo(invitation, InvitationUpdateDTO.class));
        }
        catch(Exception ex){

            return "error.403";
        }
        return "event.list";
    }


}
