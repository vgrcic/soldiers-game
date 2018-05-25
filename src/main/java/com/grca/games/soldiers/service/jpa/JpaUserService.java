package com.grca.games.soldiers.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.repository.jpa.JpaUserRepository;
import com.grca.games.soldiers.service.UserService;

@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private JpaUserRepository repository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username + " not found.");
		return user;
	}

	@Override
	public User save(User user) {
		if (user == null)
			return null;
		return repository.save(user);
	}

}
