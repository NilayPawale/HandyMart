package com.example.demo.entities;

public class LoginCheck {
     String username,password_hash;
     Boolean status_approve;
     

	public String getUsername() {
		return username;
	}

	public Boolean isStatus_approve() {
		return status_approve;
	}

	public void setStatus_approve(Boolean status_approve) {
		this.status_approve = status_approve;
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
     
     
}
