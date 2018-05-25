package com.grca.games.soldiers.support.converter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.model.dto.UserDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserConverterTest {

	@Autowired
	private UserConverter converter;

	@Test
	public void testInstance() {
		assertNotNull(converter);
	}
	
	@Test
	public void testConvertNullToUser() {
		User user = converter.fromDto(null);
		assertNull(user);
	}
	
	@Test
	public void testConvertDtoToUser() {
		User user = converter.fromDto(new UserDto("username", "password", "passwordConfirmation"));
		assertNull(user.getId());
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());
	}

}
