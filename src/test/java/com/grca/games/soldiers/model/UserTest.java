package com.grca.games.soldiers.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void setUp() {
		user = new User();
	}
	
	@Test
	public void testBlankUser() {
		assertNotNull(user);
		assertNull(user.getId());
		assertNull(user.getUsername());
		assertNull(user.getPassword());
	}
	
	@Test
	public void testUserInstance() {
		user = new User(1L, "username", "password");
		assertEquals(new Long(1L), user.getId());
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());		
	}
	
	@Test
	public void testId() {
		user.setId(5L);
		assertEquals(new Long(5L), user.getId());
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
	public void testUserDetailsInterface() {
		assertNull(user.getAuthorities());
		assertTrue(user.isAccountNonExpired());
		assertTrue(user.isAccountNonLocked());
		assertTrue(user.isCredentialsNonExpired());
		assertTrue(user.isEnabled());
	}
	
}
