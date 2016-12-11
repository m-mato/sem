package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.utils.InvitationState;
import cz.muni.pa165.sem.utils.PerformanceUnits;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Locale;

/**
 * @author Vit Hovezak
 */
public abstract class BaseController {

    @ModelAttribute("language")
    public String getLanguage() {
        return LocaleContextHolder.getLocale().getLanguage();
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
