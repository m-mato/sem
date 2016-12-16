package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.facade.SportFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Matej Majdis
 */
@Controller
@RequestMapping("/admin")
public class SportController extends BaseController {

	@Autowired
	private SportFacade sportFacade;

	@RequestMapping("/sports")
	public String renderSports(Model model) {

		model.addAttribute("sports", sportFacade.getAllSports());

		return "sports";
	}
}
