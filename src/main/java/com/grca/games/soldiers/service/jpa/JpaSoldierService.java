package com.grca.games.soldiers.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grca.games.soldiers.model.Soldier;
import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.repository.jpa.JpaSoldierRepository;
import com.grca.games.soldiers.service.SoldierService;

@Service
public class JpaSoldierService implements SoldierService {
	
	@Autowired
	private JpaSoldierRepository repository;
	@Autowired
	private JpaUserService userService;

	@Override
	public Soldier get(Long id) {
		if (id == null)
			return null;
		return repository.findOne(id);
	}

	@Override
	public Soldier save(Soldier soldier, String username) {
		User user = userService.getByUsername(username);
		if (user == null || getByNameAndUsername(soldier.getName(), username) != null)
			return null;
		soldier.setUser(user);
		return repository.save(soldier);
	}

	@Override
	public Soldier getByNameAndUsername(String name, String username) {
		if (name == null || username == null)
			return null;
		return repository.findByNameAndUserUsername(name, username);
	}

}
