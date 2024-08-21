package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerReg;
import com.example.demo.entities.Login;
import com.example.demo.entities.Role;
import com.example.demo.entities.Vendor;
import com.example.demo.entities.VendorReg;
import com.example.demo.services.LoginService;
import com.example.demo.services.RoleService;
import com.example.demo.services.VendorService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VendorController {
	@Autowired
	VendorService vservice;
	
	@Autowired
	LoginService lservice;
	@Autowired
	RoleService rservice;
	
	@GetMapping("/getVendor")
	public Vendor getVendor(@RequestParam("login_id") int login_id) {
		Login l=lservice.getById(login_id);
		return vservice.getVendor(l);
	}
	
	@GetMapping("/getallVendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vservice.getAllVendors();
        return ResponseEntity.ok().body(vendors);
    }
	
	@PostMapping("/regVendor")
	public Vendor regVendor(@RequestBody VendorReg vreg)
	{
		System.out.println("In Vendor Method");
		try {
			System.out.println(vreg);
		Role r = rservice.getRole(3);
		Login l = new Login(vreg.getUsername(),vreg.getPassword_hash(),r,false);
		Login saved = lservice.saveLogin(l);
	   Vendor v = new Vendor(vreg.getFirstname(),vreg.getLastname(),vreg.getEmail(),vreg.getPhone(),vreg.getAddress(),vreg.getAadharnumber(),vreg.getSkillset(),l);
	   return vservice.registerVendor(v);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	

	
	

}
