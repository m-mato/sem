package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.facade.SportsmanFacade;
import cz.muni.pa165.sem.utils.InvitationState;
import cz.muni.pa165.sem.utils.PerformanceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Vit Hovezak
 */
public abstract class BaseController {

    @Autowired
    private SportsmanFacade sportsmanFacade;

    @ModelAttribute("language")
    public String getLanguage() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    @ModelAttribute("loggedUserName")
    public String getloggedUserName(Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()) {
            return sportsmanFacade.getByEmail(authentication.getName()).getName();
        }
        return "";
    }

    @ModelAttribute("invitationStates")
    public InvitationState[] getInvitationStates() {
        return InvitationState.values();
    }

    @ModelAttribute("performanceUnits")
    public PerformanceUnits[] getPerformanceUnits() {
        return PerformanceUnits.values();
    }

}
