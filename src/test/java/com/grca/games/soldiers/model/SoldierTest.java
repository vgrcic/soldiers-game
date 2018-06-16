package com.grca.games.soldiers.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SoldierTest {
	
	private Soldier soldier;
	
	@Before
	public void setUp() {
		soldier = new Soldier();
	}

	@Test
	public void testInstance() {
		assertNotNull(soldier);
	}
	
	@Test
	public void testSoldierInstance() {
		soldier = new Soldier(1L, "soldierName", new User());
		assertEquals(new Long(1L), soldier.getId());
		assertEquals("soldierName", soldier.getName());
		assertNotNull(soldier.getUser());		
	}
	
	@Test
	public void testId() {
		assertNull(soldier.getId());
		soldier.setId(1L);
		assertEquals(new Long(1L), soldier.getId());
	}
	
	@Test
	public void testName() {
		assertNull(soldier.getName());
		soldier.setName("soldierName");
		assertEquals("soldierName", soldier.getName());
	}
	
	@Test
	public void testUser() {
		assertNull(soldier.getUser());
		User user = new User();
		soldier.setUser(user);
		assertEquals(user, soldier.getUser());
	}

}
