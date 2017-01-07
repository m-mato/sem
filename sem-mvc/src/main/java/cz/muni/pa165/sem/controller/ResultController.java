package cz.muni.pa165.sem.controller;

import cz.muni.pa165.sem.dto.*;
import cz.muni.pa165.sem.editor.EventEditor;
import cz.muni.pa165.sem.editor.SportsmanEditor;
import cz.muni.pa165.sem.facade.EventFacade;
import cz.muni.pa165.sem.facade.ResultFacade;
import cz.muni.pa165.sem.facade.SportsmanFacade;
import cz.muni.pa165.sem.service.BeanMappingService;
import cz.muni.pa165.sem.utils.PerformanceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vit Hovezak
 */
@Controller
@RequestMapping("/results")
public class ResultController extends BaseController {

	@Autowired
	private EventFacade eventFacade;

	@Autowired
	private ResultFacade resultFacade;

	@Autowired
	private SportsmanFacade sportsmanFacade;

	@Autowired
	private BeanMappingService beanMappingService;

	@ModelAttribute("performanceUnits")
	public PerformanceUnits[] getPerformanceUnits() {
		return PerformanceUnits.values();
	}

	@ModelAttribute("events")
	public List<EventDTO> getEvents() {
		return eventFacade.findAll();
	}

	@ModelAttribute("sportsmans")
	public List<SportsmanDTO> getSportsmans() {
		return sportsmanFacade.getAll();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(EventDTO.class, new EventEditor(eventFacade));
		binder.registerCustomEditor(SportsmanDTO.class, new SportsmanEditor(sportsmanFacade));
	}

	@RequestMapping
	public String renderList(Model model) {
		List<ResultDTO> results = new ArrayList<>();
		List<ResultDTO> allResults = resultFacade.findAll();
		for (ResultDTO result : allResults) {
			if (result.getPerformance() >= 0 && result.getPosition() >= 0) {
				results.add(result);
			}
		}
		model.addAttribute("results", results);
		return "result.list";
	}

	@RequestMapping("/{id}")
	public Object renderDetail(@PathVariable("id") Long id, Model model) {
		ResultDTO resultDTO = resultFacade.findById(id);
		if (resultDTO == null) {
			return redirect("/results");
		}
		model.addAttribute("result", resultDTO);
		List<ResultDTO> results = resultFacade.findByEvent(resultDTO.getEvent());
		results.remove(resultDTO);
		model.addAttribute("results", results);
		return "result.detail";
	}

	@RequestMapping("/create")
	public String renderCreate(@RequestParam(value = "event", required = false) Long eventId, Model model) {
		ResultCreateDTO resultCreateDTO = new ResultCreateDTO();
		if (eventId != null) {
			EventDTO eventDTO = eventFacade.findById(eventId);
			resultCreateDTO.setEvent(eventDTO);
		}
		model.addAttribute("result", resultCreateDTO);
		return "result.create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Object processCreate(@ModelAttribute("result") @Valid ResultCreateDTO resultCreateDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return "result.create";
		}
		ResultDTO resultDTO = resultFacade.create(resultCreateDTO);
		return redirect("/results/" + resultDTO.getId() + "?create");
	}

	@RequestMapping("/{id}/update")
	public Object renderUpdate(@PathVariable("id") Long id, Model model) {
		ResultDTO resultDTO = resultFacade.findById(id);
		if (resultDTO == null) {
			return redirect("/results");
		}
		model.addAttribute("result", beanMappingService.mapTo(resultDTO, ResultUpdateDTO.class));
		return "result.update";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public Object processUpdate(@Valid @ModelAttribute("result") ResultUpdateDTO resultUpdateDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return "result.update";
		}
		resultFacade.update(resultUpdateDTO);
		return redirect("/results/" + resultUpdateDTO.getId() + "?update");
	}

	@RequestMapping("/{id}/delete")
	public Object renderDelete(@PathVariable("id") Long id) {
		ResultDTO resultDTO = resultFacade.findById(id);
		if (resultDTO != null) {
			resultFacade.delete(resultDTO.getId());
		}
		return redirect("/results?delete");
	}

}
