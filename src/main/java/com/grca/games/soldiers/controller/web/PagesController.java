package com.grca.games.soldiers.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {
	
	@RequestMapping("/")
	ModelAndView index() {
		return new ModelAndView("index");
	}

}
