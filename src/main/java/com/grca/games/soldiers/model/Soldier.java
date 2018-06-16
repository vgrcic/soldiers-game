package com.grca.games.soldiers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="soldiers")
public class Soldier {
	
	@Id
	@Column
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Soldier(Long id, String name, User user) {
		this.id = id;
		this.name = name;
		this.user = user;
	}
	
	public Soldier() {}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

}
