package com.example.demo.entities;

import jakarta.persistence.Column;

public class VendorReg {
int vendor_id;

	String firstname;
	
	String lastname;
	
	String email;
	
	String phone;
	String address;
	String aadharnumber;

	String skillset;

	String username;
	
	String password_hash;
	
	boolean status_approve;

	public VendorReg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendorReg(int vendor_id, String firstname, String lastname, String email, String phone, String address,
			String aadharnumber, String skillset, String username, String password_hash, boolean status_approve) {
		super();
		this.vendor_id = vendor_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.aadharnumber = aadharnumber;
		this.skillset = skillset;
		this.username = username;
		this.password_hash = password_hash;
		this.status_approve = status_approve;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
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

	public String getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public String getSkillset() {
		return skillset;
	}

	public void setSkillset(String skillset) {
		this.skillset = skillset;
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
		return "VendorReg [vendor_id=" + vendor_id + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", phone=" + phone + ", address=" + address + ", aadharnumber=" + aadharnumber + ", skillset="
				+ skillset + ", username=" + username + ", password_hash=" + password_hash + ", status_approve="
				+ status_approve + "]";
	}

	

	
	
	
	
	
	
}
