package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Login;
import com.example.demo.entities.Vendor;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.VendorRepository;

import jakarta.transaction.Transactional;

@Service
public class LoginService {
	@Autowired
    LoginRepository lrepo;
	
	@Autowired
	VendorRepository vrepo;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public Login getLogin(String username,String password_hash) {
		Login l;
		Optional<Login> ol=lrepo.getLogin(username, password_hash);
		System.out.println(ol+"*************");
		try {
			l=ol.get();
		}
		catch(Exception e) {
			l=null;
		}
		return l;
	}
	
	
	@Transactional
    public Login saveLogin(Login login) {
        try {
            return lrepo.save(login);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception stack trace
            throw new RuntimeException("Error saving login information.", e);
        }
    }
	
	 public Login getById(int loginId) {
	        try {
	            return lrepo.findById(loginId).get();
	        } catch (Exception e) {
	            e.printStackTrace(); // Log the exception stack trace
	            throw new RuntimeException("Error getting login information by ID.", e);
	        }
	    }
	 
//	 public void approveLogin(int login_id) {
//	        // Fetch the login by ID
//	        Login login = lrepo.findById(login_id).get();
//	               
//
//	        // Perform any business logic related to approving the login
//	        login.setStatus_approve(true);
//
//	        // Save the updated login entity
//	        lrepo.save(login);
//	    }

	    public void rejectLogin(int login_id) {
	        // Fetch the login by ID
	        Login login = lrepo.findById(login_id).get();
	               

	        // Perform any business logic related to rejecting the login
	        login.setStatus_approve(false);

	        // Save the updated login entity
	        lrepo.save(login);
	    }

	    
	    public void approveLogin(int login_id) {
	        lrepo.findById(login_id).ifPresent(login -> {
	            // Perform the approval logic here
	            // For example, set an approval status field on the Login entity
	            login.setStatus_approve(true);
	            lrepo.save(login);
	        });
	        // If the login is not present, no need to explicitly handle it
	    }


		public int getCustomerId(int login_id) {
			return customerRepository.findByCustomerByLoginID(login_id).getCustomer_id();
			
		}
	 
	    public void resetPassword(String emailOrUsername) {
	        Optional<Login> loginOptional = lrepo.getLoginByUser(emailOrUsername);
	        
	        if (loginOptional.isPresent()) {
	            Login login = loginOptional.get();
	            // Generate a temporary password or send a reset link via email.
	            String tempPassword = generateTemporaryPassword();
	            login.setPassword_hash(tempPassword);
	            lrepo.save(login);

	            // Optionally, send an email with the new password or reset link.
	            sendPasswordResetEmail(login.getUsername(), tempPassword);
	        } else {
	            throw new RuntimeException("User not found");
	        }
	    }

	    private String generateTemporaryPassword() {
	        // Implement password generation logic here
	        return "Temp@1234"; // Example, replace with actual generation logic
	    }

	    private void sendPasswordResetEmail(String email, String tempPassword) {
	        // Implement email sending logic here
	        System.out.println("Sending email to " + email + " with temp password: " + tempPassword);
	    }
}
	 
	


