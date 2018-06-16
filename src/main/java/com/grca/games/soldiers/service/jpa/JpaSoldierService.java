package com.grca.games.soldiers.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grca.games.soldiers.model.Soldier;
import com.grca.games.soldiers.repository.jpa.JpaSoldierRepository;
import com.grca.games.soldiers.service.SoldierService;

@Service
public class JpaSoldierService implements SoldierService {
	
	@Autowired
	private JpaSoldierRepository repository;

	@Override
	public Soldier get(Long id) {
		if (id == null)
			return null;
		return repository.findOne(id);
	}

}
