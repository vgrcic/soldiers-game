package com.grca.games.soldiers.controller.api;

import java.util.Collection;

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
import com.grca.games.soldiers.model.dto.SoldierDto;
import com.grca.games.soldiers.service.SoldierService;
import com.grca.games.soldiers.support.converter.SoldierConverter;

@Controller
@RequestMapping("api/soldiers")
public class SoldierController {
	
	@Autowired
	private SoldierService soldierService;
	@Autowired
	private SoldierConverter dto;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<SoldierDto> get(@PathVariable Long id, WebRequest request) {
		Soldier soldier = soldierService.get(id);
		if (soldier == null)
			return new ResponseEntity<SoldierDto>(HttpStatus.NOT_FOUND);
		if (!soldier.belongsTo(request.getRemoteUser()))
			return new ResponseEntity<SoldierDto>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<SoldierDto>(dto.withoutUser(soldier), HttpStatus.OK);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Soldier> save(@RequestBody @Valid Soldier soldier, BindingResult result, WebRequest request) {
		String username = request.getRemoteUser();
		if (result.hasErrors())
			return new ResponseEntity<Soldier>(HttpStatus.BAD_REQUEST);
		soldier = soldierService.save(soldier, username);
		if (soldier == null)
			return new ResponseEntity<Soldier>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Soldier>(soldier, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<Collection<SoldierDto>> get(WebRequest request) {
		Collection<Soldier> soldiers = soldierService.getForUser(request.getRemoteUser());
		return new ResponseEntity<Collection<SoldierDto>>(dto.withoutUsers(soldiers), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Soldier> delete(@PathVariable Long id, WebRequest request) {
		Soldier soldier = soldierService.get(id);
		if (!soldier.belongsTo(request.getRemoteUser()))
			return new ResponseEntity<Soldier>(HttpStatus.UNAUTHORIZED);
		soldierService.delete(id);
		return new ResponseEntity<Soldier>(HttpStatus.OK);
	}
	
}
