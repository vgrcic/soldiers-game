package com.grca.games.soldiers.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {
	
	@RequestMapping("/")
	ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login")
	ModelAndView loginPage(WebRequest request) {
		if (request.getRemoteUser() != null)
			return new ModelAndView("redirect:/");
		return new ModelAndView("auth/login");
	}
	
	@RequestMapping(value="/register")
	ModelAndView registerPage(WebRequest request) {
		if (request.getRemoteUser() != null)
			return new ModelAndView("redirect:/");
		return new ModelAndView("auth/register");
	}

}
