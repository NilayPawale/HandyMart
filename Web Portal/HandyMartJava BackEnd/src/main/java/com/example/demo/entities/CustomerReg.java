package com.example.demo.entities;

import jakarta.persistence.Column;

public class CustomerReg {
	int customer_id;
	
	String firstname;
	
	String lastname;
	
	String email;
	
	String phone;
	
	String address;
	
	String username;
	
	String password_hash;
	
	boolean status_approve;

	public CustomerReg() {
		super();
	}

	public CustomerReg(String firstname, String lastname, String email, String phone, String address,
			String username, String password_hash, boolean status_approve) {
		super();
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.username = username;
		this.password_hash = password_hash;
		this.status_approve = status_approve;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public boolean isStatus_approve() {
		return status_approve;
	}

	public void setStatus_approve(boolean status_approve) {
		this.status_approve = status_approve;
	}

	@Override
	public String toString() {
		return "CustomerReg [customer_id=" + customer_id + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", username=" + username
				+ ", password_hash=" + password_hash + ", status_approve=" + status_approve + "]";
	}

	

	

	
	
}
