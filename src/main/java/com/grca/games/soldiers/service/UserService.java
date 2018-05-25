package com.grca.games.soldiers.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.grca.games.soldiers.model.User;

public interface UserService extends UserDetailsService {
	
	User save(User user);
	
	User getByUsername(String username);
	
	boolean userExists(String username);

}
