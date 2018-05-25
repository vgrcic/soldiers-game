package com.grca.games.soldiers.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	@NotNull
	@Size(min=3, max=100)
	private String username;
	
	@NotNull
	@Size(min=3, max=100)
	private String password;
	
	@NotNull
	private String passwordConfirmation;
	
	public UserDto() {}

	public UserDto(String username, String password, String passwordConfirmation) {
		this.username = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public boolean hasMatchingPasswords() {
		return password.equals(passwordConfirmation);
	}

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getPasswordConfirmation() { return passwordConfirmation; }
	public void setPasswordConfirmation(String passwordConfirmation) { this.passwordConfirmation = passwordConfirmation; }	

}
