package com.rctecnologia.aciot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Modelo de usuário para controle de acesso.
 * @author rogerio
 *
 */
@Entity
public class User {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;	  

	  @Column(name = "name")
	  private String name;	
	  
	  @Column(name = "username")
	  private String username;
	  
	  @Column(name = "password")
	  private String password;
	  
	  @Column(name = "role")
	  private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
	  
	  

}
