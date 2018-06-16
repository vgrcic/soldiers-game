package com.grca.games.soldiers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name="soldiers", uniqueConstraints=@UniqueConstraint(columnNames={"name", "user_id"}))
public class Soldier {
	
	@Id
	@Column
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=3, max=50)
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
