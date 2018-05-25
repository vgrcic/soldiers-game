package com.grca.games.soldiers.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.repository.jpa.JpaUserRepository;
import com.grca.games.soldiers.service.UserService;

@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private JpaUserRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;

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
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Override
	public User getByUsername(String username) {
		if (username == null)
			return null;
		return repository.findByUsername(username);
	}

}
