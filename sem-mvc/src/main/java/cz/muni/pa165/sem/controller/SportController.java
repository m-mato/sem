package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.SportCreateDTO;
import cz.muni.pa165.sem.dto.SportDTO;
import cz.muni.pa165.sem.dto.SportUpdateDTO;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.facade.SportFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Matej Majdis
 */
@Controller
@RequestMapping("/sports")
public class SportController extends BaseController {

	@Autowired
	private EventFacade eventFacade;

	@Autowired
	private SportFacade sportFacade;

	@Autowired
	private BeanMappingService beanMappingService;

	@RequestMapping
	public String renderList(Model model) {
		model.addAttribute("sports", sportFacade.getAllSports());
		return "sport.list";
	}

	@RequestMapping("/{id}")
	public String renderDetail(@PathVariable("id") Long id, Model model) {
		SportDTO sportDTO = sportFacade.getSportById(id);
		if (sportDTO == null) {
			return "redirect:/sports";
		}
		model.addAttribute("sport", sportDTO);
		model.addAttribute("events", eventFacade.findBySport(sportDTO.getId()));
		return "sport.detail";
	}

	@RequestMapping("/create")
	public String renderCreate(Model model) {
		model.addAttribute("sport", new SportCreateDTO());
		return "sport.create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String processCreate(@ModelAttribute("sport") @Valid SportCreateDTO sportCreateDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return "sport.create";
		}
		sportFacade.create(sportCreateDTO);
		return "redirect:/sports?create";
	}

	@RequestMapping("/{id}/update")
	public String renderUpdate(@PathVariable("id") Long id, Model model) {
		SportDTO sportDTO = sportFacade.getSportById(id);
		if (sportDTO == null) {
			return "redirect:/sports";
		}
		model.addAttribute("sport", beanMappingService.mapTo(sportDTO, SportUpdateDTO.class));
		return "sport.update";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String processUpdate(@Valid @ModelAttribute("sport") SportUpdateDTO sportUpdateDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return "sport.update";
		}
		sportFacade.update(sportUpdateDTO);
		return "redirect:/sports?update";
	}

	@RequestMapping("/{id}/delete")
	public String renderDelete(@PathVariable("id") Long id) {
		SportDTO sportDTO = sportFacade.getSportById(id);
		if (sportDTO != null) {
			sportFacade.delete(sportDTO.getId());
		}
		return "redirect:/sports?delete";
	}

}
