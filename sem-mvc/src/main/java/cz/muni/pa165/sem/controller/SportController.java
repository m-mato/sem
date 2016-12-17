package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.SportCreateDTO;
import cz.muni.pa165.sem.facade.SportFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Matej Majdis
 */
@Controller
@RequestMapping("/admin")
public class SportController extends BaseController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private SportFacade sportFacade;

	@RequestMapping("/sports")
	public String renderSports(Model model) {

		model.addAttribute("sports", sportFacade.getAllSports());

		return "sport.list";
	}

	@RequestMapping(value = "/sport/create", method = RequestMethod.POST)
	public String createSport(@ModelAttribute("SpringWeb")SportCreateDTO sportCreateDTO, BindingResult result,
							  HttpServletResponse resp) throws IOException {

		sportFacade.create(sportCreateDTO);

		resp.sendRedirect(servletContext.getContextPath() + "/admin/sports");
		return null;
	}
}
