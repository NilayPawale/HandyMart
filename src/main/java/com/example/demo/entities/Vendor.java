package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vendors")
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int vendor_id;
	
	@Column
	String firstname;
	@Column
	String lastname;
	@Column
	String email;
	@Column
	String phone;
	@Column
	String Address ;
	@Column
	String aadharnumber;
	@Column
	String skillset;
	@OneToOne
	@JoinColumn(name="login_id")
	Login login_id;
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
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
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
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
	public Login getLogin_id() {
		return login_id;
	}
	public void setLogin_id(Login login_id) {
		this.login_id = login_id;
	}
	public Vendor(String firstname, String lastname, String email, String phone, String address,
			String aadharnumber, String skillset, Login login_id) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		Address = address;
		this.aadharnumber = aadharnumber;
		this.skillset = skillset;
		this.login_id = login_id;
	}
	
	public Vendor(int vendor_id, String firstname, String lastname, String email, String phone, String address,
			String aadharnumber, String skillset, Login login_id) {
		super();
		this.vendor_id = vendor_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		Address = address;
		this.aadharnumber = aadharnumber;
		this.skillset = skillset;
		this.login_id = login_id;
	}
	@Override
	public String toString() {
		return "Vendor [vendor_id=" + vendor_id + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", phone=" + phone + ", Address=" + Address + ", aadharnumber=" + aadharnumber + ", skillset="
				+ skillset + ", login_id=" + login_id + "]";
	}
	
	
	
	

}
