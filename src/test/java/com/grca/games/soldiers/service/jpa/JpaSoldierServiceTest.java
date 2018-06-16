package com.grca.games.soldiers.service.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.grca.games.soldiers.service.SoldierService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaSoldierServiceTest {
	
	@Autowired
	private SoldierService soldierService;

	@Test
	public void testAutowiring() {
		assertNotNull(soldierService);
	}

}
