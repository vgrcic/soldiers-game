package com.grca.games.soldiers.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue
	private Long id;
	
	@Column(length=100, unique=true, nullable=false)
	private String username;
	
	@Column(length=100, nullable=false)
	private String password;
	
	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public User() {}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	
	public Collection<? extends GrantedAuthority> getAuthorities() { return null; }
	public String getPassword() { return password; }
	public String getUsername() { return username; }
	public boolean isAccountNonExpired() { return true; }
	public boolean isAccountNonLocked() { return true; }
	public boolean isCredentialsNonExpired() { return true; }
	public boolean isEnabled() { return true; }

}
