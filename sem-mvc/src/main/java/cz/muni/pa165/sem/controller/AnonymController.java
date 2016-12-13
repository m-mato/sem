package cz.muni.pa165.sem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Matej Majdis
 */
@Controller
public class AnonymController extends BaseController {

	@RequestMapping("/find-event")
	public String renderFindEvent() {
		return "find-event";
	}
}
