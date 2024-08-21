package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Login {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int login_id;
	@Column
	String username;
	@Column
	String password_hash;
	@Column
	Boolean status_approve;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	Role role_id;
	
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Login(String username, String password_hash, Role role_id, Boolean status_approve) {
		super();
		this.username = username;
		this.password_hash = password_hash;
		this.status_approve = status_approve;
		this.role_id = role_id;
	}




	public Login(String username, String password_hash) {
		super();
		this.username = username;
		this.password_hash = password_hash;
	}

	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public Role getRole_id() {
		return role_id;
	}
	public void setRole_id(Role role_id) {
		this.role_id = role_id;
	}




	public Boolean isStatus_approve() {
		return status_approve;
	}




	public void setStatus_approve(Boolean status_approve) {
		this.status_approve = status_approve;
	}




	@Override
	public String toString() {
		return "Login [login_id=" + login_id + ", username=" + username + ", password_hash=" + password_hash
				+ ", status_approve=" + status_approve + ", role_id=" + role_id + "]";
	}

	
	
	
	
	
}
