package com.grca.games.soldiers.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grca.games.soldiers.model.User;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
