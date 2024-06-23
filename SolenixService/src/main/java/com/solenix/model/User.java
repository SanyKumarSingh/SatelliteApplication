/**
 * 
 */
package com.solenix.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * @author a5143522
 *
 */
@Entity
@Table(name = "ad_user")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "name")
    private String userName;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "role")
    private String role;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<EventRequest> eventRequested;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<EventRequest> getEventRequested() {
		return eventRequested;
	}

	public void setEventRequested(List<EventRequest> eventRequested) {
		this.eventRequested = eventRequested;
	}
	
}
