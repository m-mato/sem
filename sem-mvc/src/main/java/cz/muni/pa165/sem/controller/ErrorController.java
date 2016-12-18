package cz.muni.pa165.sem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vit Hovezak
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {

    @RequestMapping("/403")
    public String render403() {
        return "error.403";
    }

    @RequestMapping("/404")
    public String render404() {
        return "error.404";
    }

}
