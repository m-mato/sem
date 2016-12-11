package cz.muni.pa165.sem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vit Hovezak
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @RequestMapping
    public String renderLogin() {
        return "login";
    }

}
