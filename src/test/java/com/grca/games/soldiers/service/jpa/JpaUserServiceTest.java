package com.grca.games.soldiers.service.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup(value = {
		@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:before.sql")
})
public class JpaUserServiceTest {
	
	@Autowired
	private JpaUserService service;

	@Test
	public void testAutowiring() {
		assertNotNull(service);
	}
	
	@Test
	public void testLoadUserByUsername() {
		UserDetails user = service.loadUserByUsername("user");
		assertNotNull(user);
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void testLoadNonExistingUserByUsername() {
		service.loadUserByUsername("notauser");
	}

}
