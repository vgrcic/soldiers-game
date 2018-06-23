package com.grca.games.soldiers.service;

import java.util.Collection;

import com.grca.games.soldiers.model.Soldier;

public interface SoldierService {
	
	/**
	 * Retrieves a Soldier entity by its ID.
	 * @param id - {@code Long}
	 * @return {@code Soldier} entity with the given ID or {@code null} if none is found.
	 */
	Soldier get(Long id);
	
	/**
	 * Persists a {@code Soldier} entity.<br/>
	 * Validation is performed to ensure that the user exists and that a soldier with the same name doesn't exist.
	 * @param soldier entity
	 * @param username of the user that owns the soldier
	 * @return Saved entity or {@code null} if error occurred
	 */
	Soldier save(Soldier soldier, String username);
	
	/**
	 * Retrieves a {@code Soldier} entity that has a given name and belongs to a specified user.
	 * @param name of the soldier
	 * @param username of the user
	 * @return {@code Soldier} entity or {@code null} if none found
	 */
	Soldier getByNameAndUsername(String name, String username);
	
	/**
	 * Retrieves all {@code Soldier} entities for a given user.
	 * @param username
	 * @return {@code Collection<Soldier>}
	 */
	Collection<Soldier> getForUser(String username);
	
	/**
	 * Deletes a {@code Soldier} entity with a given ID.
	 * @param id - {@code Long}
	 */
	void delete(Long id);

}
