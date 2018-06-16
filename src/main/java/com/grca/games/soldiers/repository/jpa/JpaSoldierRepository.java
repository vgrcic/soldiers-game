package com.grca.games.soldiers.repository.jpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grca.games.soldiers.model.Soldier;

@Repository
public interface JpaSoldierRepository extends JpaRepository<Soldier, Long> {
	
	Soldier findByNameAndUserUsername(String name, String username);
	
	Collection<Soldier> findByUserUsername(String username);

}
