package com.grca.games.soldiers.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.grca.games.soldiers.model.dto.UserDto;
import com.grca.games.soldiers.service.UserService;
import com.grca.games.soldiers.support.converter.UserConverter;

@Controller
public class PagesController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserConverter userConverter;
	
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
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	ModelAndView registerPage(WebRequest request) {
		if (request.getRemoteUser() != null)
			return new ModelAndView("redirect:/");
		return new ModelAndView("auth/register");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	ModelAndView register(@Valid @ModelAttribute("user") UserDto user, BindingResult result, WebRequest request) {
		ModelAndView mav = new ModelAndView();
		if (request.getRemoteUser() != null)
			return new ModelAndView("redirect:/");
		if (result.hasErrors() || !user.hasMatchingPasswords()) {
			mav.setViewName("auth/register");
			mav.setStatus(HttpStatus.BAD_REQUEST);
			return mav;
		}
		userService.save(userConverter.fromDto(user));
		mav.setViewName("redirect:/login");
		mav.setStatus(HttpStatus.OK);
		return mav;
	}

}
