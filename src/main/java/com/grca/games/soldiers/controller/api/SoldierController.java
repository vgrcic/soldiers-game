package com.grca.games.soldiers.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.grca.games.soldiers.model.Soldier;
import com.grca.games.soldiers.service.SoldierService;
import com.grca.games.soldiers.service.UserService;

@Controller
@RequestMapping("/api/soldier")
public class SoldierController {
	
	@Autowired
	private SoldierService soldierService;
	@Autowired
	private UserService userService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Soldier> get(@PathVariable Long id, WebRequest request) {
		Soldier soldier = soldierService.get(id);
		if (soldier == null)
			return new ResponseEntity<Soldier>(HttpStatus.NOT_FOUND);
		if (!soldier.getUser().getUsername().equals(request.getRemoteUser()))
			return new ResponseEntity<Soldier>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<Soldier>(soldier, HttpStatus.OK);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Soldier> save(@RequestBody @Valid Soldier soldier, BindingResult result, WebRequest request) {
		String username = request.getRemoteUser();
		if (result.hasErrors() || soldierService.getByNameAndUsername(soldier.getName(), username) != null)
			return new ResponseEntity<Soldier>(HttpStatus.BAD_REQUEST);
		if (userService.getByUsername(username) == null)
			return new ResponseEntity<Soldier>(HttpStatus.UNAUTHORIZED);
		soldier = soldierService.save(soldier, username);
		return new ResponseEntity<Soldier>(soldier, HttpStatus.CREATED);
	}
	
}
