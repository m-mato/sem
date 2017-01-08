package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.SportsmanDTO;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;

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

    @ModelAttribute("loggedUser")
    public SportsmanDTO getLoggedUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return sportsmanFacade.getByEmail(authentication.getName());
        }
        return null;
    }

    /**
     * Sends redirect so string model attributes are not visible in URL.
     */
    protected RedirectView redirect(String url) {
        RedirectView view = new RedirectView(url, true);
        view.setExposeModelAttributes(false);
        return view;
    }

}
