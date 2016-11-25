package cz.muni.pa165.sem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Matej Majdis
 */
@Controller
public class HealthCheckController {

	@RequestMapping("/")
	public String health() {

		return "redirect:/health";
	}
}
