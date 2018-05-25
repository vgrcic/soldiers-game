package com.grca.games.soldiers.support.validator;

import static org.junit.Assert.*;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.grca.games.soldiers.model.dto.UserDto;

public class UserValidationTest {

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void test() {
		assertNotNull(validator);
	}
	
	@Test
	public void testNullUsername() {
		UserDto user = new UserDto(null, "password", "password");
		assertFalse(validator.validate(user).isEmpty());
	}
	
	@Test
	public void testShortUsername() {
		UserDto user = new UserDto("us", "password", "password");
		assertFalse(validator.validate(user).isEmpty());
	}
	
	@Test
	public void testLongUsername() {
		UserDto user = new UserDto("0123456789001234567890012345678900123456789001234567890"
								 + "0123456789001234567890012345678900123456789001234567890", "password", "password");
		assertFalse(validator.validate(user).isEmpty());
	}
	
	@Test
	public void testShortPassword() {
		UserDto user = new UserDto("username", "pa", "password");
		assertFalse(validator.validate(user).isEmpty());		
	}
	
	@Test
	public void testLongPassword() {
		UserDto user = new UserDto("username", "0123456789001234567890012345678900123456789001234567890"
				 + "0123456789001234567890012345678900123456789001234567890", "password");
		assertFalse(validator.validate(user).isEmpty());
	}
	
	@Test
	public void testNullPassword() {
		UserDto user = new UserDto("username", null, "password");
		assertFalse(validator.validate(user).isEmpty());		
	}
	
	@Test
	public void testNullPasswordConfirmation() {
		UserDto user = new UserDto("username", "password", null);
		assertFalse(validator.validate(user).isEmpty());		
	}
	
	@Test
	public void testValidUser() {
		UserDto user = new UserDto("use", "pas", "password");
		assertTrue(validator.validate(user).isEmpty());		
	}

}
