package com.grca.games.soldiers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.grca.games.soldiers.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoldierDto {
	
	private Long id;
	
	private String name;
	
	private User user;
	
	public SoldierDto(Long id, String name, User user) {
		this.id = id;
		this.name = name;
		this.user = user;
	}
	
	public SoldierDto() {}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

}
