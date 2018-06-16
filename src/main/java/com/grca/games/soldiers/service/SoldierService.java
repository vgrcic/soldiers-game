package com.grca.games.soldiers.service;

import com.grca.games.soldiers.model.Soldier;

public interface SoldierService {
	
	Soldier get(Long id);
	
	Soldier save(Soldier soldier, String username);
	
	Soldier getByNameAndUsername(String name, String username);

}
