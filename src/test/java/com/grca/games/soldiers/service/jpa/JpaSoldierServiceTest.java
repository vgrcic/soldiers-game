package com.grca.games.soldiers.service.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.grca.games.soldiers.model.Soldier;
import com.grca.games.soldiers.service.SoldierService;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup(value = {
		@Sql(executionPhase=ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:before.sql")
})
public class JpaSoldierServiceTest {
	
	@Autowired
	private SoldierService soldierService;

	@Test
	public void testAutowiring() {
		assertNotNull(soldierService);
	}
	
	@Test
	public void testGetSoldier() {
		Soldier soldier = soldierService.get(1L);
		assertNotNull(soldier);
	}
	
	@Test
	public void testGetNonExistantSoldier() {
		Soldier soldier = soldierService.get(2L);
		assertNull(soldier);
	}
	
	@Test
	public void testGetSoldierByNullID() {
		Soldier soldier = soldierService.get(null);
		assertNull(soldier);
	}
	
	@Test
	public void testSaveSoldier() {
		Soldier soldier = soldierService.save(new Soldier(null, "newsoldier", null), "player");
		assertNotNull(soldier);
	}

	@Test
	public void testSaveSoldierWithNonExistantUser() {
		Soldier soldier = soldierService.save(new Soldier(null, "newsoldier", null), "playen");
		assertNull(soldier);
	}
	
	@Test
	public void testSaveSoldierWithNullUser() {
		Soldier soldier = soldierService.save(new Soldier(null, "newsoldier", null), null);
		assertNull(soldier);
	}

}
