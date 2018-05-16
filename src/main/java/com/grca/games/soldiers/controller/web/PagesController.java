package com.grca.games.soldiers.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {
	
	@RequestMapping("/")
	ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	ModelAndView loginPage() {
		return new ModelAndView("auth/login");
	}

}
