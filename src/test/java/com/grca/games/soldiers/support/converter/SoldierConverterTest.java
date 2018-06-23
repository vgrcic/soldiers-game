package com.grca.games.soldiers.support.converter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.grca.games.soldiers.model.Soldier;
import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.model.dto.SoldierDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SoldierConverterTest {
	
	private Soldier soldier;
	
	@Autowired
	private SoldierConverter converter;

	@Before
	public void setUp() throws Exception {
		soldier = new Soldier(1L, "name", new User());
	}

	@Test
	public void testAutowiring() {
		assertNotNull(converter);
	}
	
	@Test
	public void testWithoutUser() {
		SoldierDto dto = converter.withoutUser(soldier);
		assertNull(dto.getUser());
	}
	
	@Test
	public void tesNulltWithoutUser() {
		SoldierDto dto = converter.withoutUser(null);
		assertNull(dto);
	}
	
	@Test
	public void testCollectionWithoutUser() {
		List<Soldier> soldiers = new ArrayList<Soldier>();
		soldiers.add(soldier);
		for (SoldierDto dto : converter.withoutUsers(soldiers)) {
			assertNull(dto.getUser());
		}
	}
	
	@Test
	public void testNullCollectionWithoutUser() {
		Collection<SoldierDto> dtos = converter.withoutUsers(null);
		assertNull(dtos);
	}

}
