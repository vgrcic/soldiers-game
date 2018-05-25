package com.grca.games.soldiers.model.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserDtoTest {
	
	private UserDto user;

	@Before
	public void setUp() throws Exception {
		user = new UserDto();
	}

	@Test
	public void testInstance() {
		assertNotNull(user);
	}
	
	@Test
	public void testUsername() {
		user.setUsername("username");
		assertEquals("username", user.getUsername());
	}
	
	@Test
	public void testPassword() {
		user.setPassword("password");
		assertEquals("password", user.getPassword());
	}
	
	@Test
	public void testPasswordConfirmation() {
		user.setPasswordConfirmation("password");
		assertEquals("password", user.getPasswordConfirmation());
	}
	
	@Test
	public void testFullInstance() {
		user = new UserDto("username", "password", "passwordConfirmation");
		assertNotNull(user.getUsername());
		assertNotNull(user.getPassword());
		assertNotNull(user.getPasswordConfirmation());
	}
	
	@Test
	public void testMatchingPasswords() {
		user.setPassword("match");
		user.setPasswordConfirmation("match");
		assertTrue(user.hasMatchingPasswords());
	}
	
	@Test
	public void testMismatchingPasswords() {
		user.setPassword("match");
		user.setPassword("mismatch");
		assertFalse(user.hasMatchingPasswords());
	}

}
