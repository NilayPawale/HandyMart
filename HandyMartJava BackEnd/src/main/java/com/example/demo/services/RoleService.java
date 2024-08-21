package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Login;
import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
    RoleRepository rrepo;
	
	 public Role getById(int RoleId) {
	        try {
	            return rrepo.findById(RoleId).get();
	        } catch (Exception e) {
	            e.printStackTrace(); // Log the exception stack trace
	            throw new RuntimeException("Error getting login information by ID.", e);
	        }
	    }
	 
	 public Role getRole(int id)
	 {
		 return rrepo.findById(id).get();
	 }
}
