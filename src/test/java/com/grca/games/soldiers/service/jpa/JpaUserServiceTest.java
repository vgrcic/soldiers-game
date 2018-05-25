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

import com.grca.games.soldiers.model.User;

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
	
	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		
		user = service.save(user);
		assertNotNull(user);
		assertNotNull(user.getId());
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());
	}
	
	@Test
	public void testSaveNullUser() {
		assertNull(service.save(null));
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void testLoadNonExistingUserByUsername() {
		service.loadUserByUsername("notauser");
	}
	
	@Test
	public void testGetByNullUsername() {
		assertNull(service.getByUsername(null));
	}
	
	@Test
	public void testGetByFalseUsername() {
		assertNull(service.getByUsername("notausername"));
	}
	
	@Test
	public void testGetByRealUsername() {
		User user = service.getByUsername("user");
		assertNotNull(user);
		assertNotNull(user.getId());
		assertNotNull(user.getUsername());
		assertNotNull(user.getPassword());
	}

}
