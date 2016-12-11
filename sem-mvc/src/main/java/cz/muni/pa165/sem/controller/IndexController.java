package cz.muni.pa165.sem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vit Hovezak
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @RequestMapping
    public String renderIndex(Model model) {
        model.addAttribute("isFront", true);
        return "index";
    }

}
