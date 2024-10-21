package com.example.demo.controllers;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ForgotPasswordDTO;
import com.example.demo.entities.Login;
import com.example.demo.entities.LoginCheck;
import com.example.demo.entities.Vendor;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.LoginService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class LoginController {
	@Autowired
    LoginService lservice;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@PostMapping("/checkLogin")
	public ResponseEntity<Login> checkLogin(@RequestBody LoginCheck lcheck) {
		
		 Login login=lservice.getLogin(lcheck.getUsername(), lcheck.getPassword_hash());
		 if(login!=null)
			 return ResponseEntity.status(HttpStatus.OK).body(login);
		 else
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	
//	@PostMapping("/login")
//    public ResponseEntity<?> registerLogin(@RequestBody Login loginData) {
//        try {
//            // Save the login data
//        	System.out.println(loginData);
//            Login savedLogin = lservice.saveLogin(loginData);
//            int customerId = customerRepository.findByCustomerByLoginID(savedLogin.getLogin_id()).getCustomer_id();
//            System.out.println("Saved");
//            return ResponseEntity.ok(customerId);
//        } catch (Exception e) {
//            e.printStackTrace(); // Log the exception using a logging framework like SLF4J
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login data registration.");
//        }
//    }
	
	public LoginController(LoginService loginService) {
        this.lservice = loginService;
    }



    @GetMapping("/{login_id}/reject")
    public ResponseEntity<Void> rejectLogin(@PathVariable("login_id") int login_id) {
        lservice.rejectLogin(login_id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{login_id}/approve")
    public ResponseEntity<Void> approveLogin(@PathVariable("login_id") int login_id) {
        lservice
        .approveLogin(login_id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/getCustomerId/{login_id}")
    public ResponseEntity<Integer> getCustomerId(@PathVariable("login_id") int login_id) {
        int customer_id = lservice
        .getCustomerId(login_id);
        return ResponseEntity.status(HttpStatus.OK).body(customer_id);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        try {
        	lservice.resetPassword(forgotPasswordDTO.getEmailOrUsername());
            return ResponseEntity.ok("Password reset instructions have been sent to your email.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
    

}
