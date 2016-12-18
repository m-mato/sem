package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.SportsmanCreateDTO;
import cz.muni.pa165.sem.editor.CalendarEditor;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Calendar;

/**
 * @author Vit Hovezak
 */
@Controller
@RequestMapping
public class UserController extends BaseController {

    @Autowired
    private SportsmanFacade sportsmanFacade;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Calendar.class, new CalendarEditor("yyyy/MM/dd"));
    }

    @RequestMapping("/login")
    public Object renderLogin(Principal principal) {
        if (principal != null) {
            redirect("/my-account");
        }
        return "user.login";
    }

    @RequestMapping("/register")
    public Object renderRegister(Principal principal, Model model) {
        if (principal != null) {
            return redirect("/my-account");
        }
        SportsmanCreateDTO sportsmanCreateDTO = new SportsmanCreateDTO();
        sportsmanCreateDTO.setIsManager(false);
        model.addAttribute("sportsman", sportsmanCreateDTO);
        return "user.register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object processRegister(@ModelAttribute("sportsman") @Valid SportsmanCreateDTO sportsmanCreateDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", true);
            return "user.register";
        }
        sportsmanFacade.create(sportsmanCreateDTO);
        return redirect("/login?register");
    }

}
