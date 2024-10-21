package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerReg;
import com.example.demo.entities.Login;
import com.example.demo.entities.Role;
import com.example.demo.services.CustomerService;
import com.example.demo.services.LoginService;
import com.example.demo.services.RoleService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {
	@Autowired
	CustomerService cservice;
	
	@Autowired
	LoginService lservice;
	
	@Autowired
	RoleService rservice;
	

	
	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("login_id") int login_id) {
		Login l=lservice.getById(login_id);
		return cservice.getCustomer(l);
	}
	

	
	@PostMapping("/regCustomer")
	public Customer regCustomer(@RequestBody CustomerReg creg)
	{
		System.out.println("In Cust Method");
		try {
			System.out.println(creg);
		Role r = rservice.getRole(2);
		Login l = new Login(creg.getUsername(),creg.getPassword_hash(),r,true);
		Login saved = lservice.saveLogin(l);
	   Customer d = new Customer(creg.getFirstname(),creg.getLastname(),creg.getEmail(),creg.getPhone(),creg.getAddress(),l);
	   return cservice.registerCustomer(d);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	


}

