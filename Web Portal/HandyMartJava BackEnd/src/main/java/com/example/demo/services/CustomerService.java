package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Login;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.SubProductCategoryRepository;
import com.example.demo.repositories.VendorProductRepository;


@Service
@Transactional
public class CustomerService {
	@Autowired
	CustomerRepository crepo;
	
	@Autowired
    LoginService lservice;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	VendorProductRepository vendorProductRepository;
	
	@Autowired
	SubProductCategoryRepository subProductCategoryRepository;
	
	public Customer getCustomer(Login l) {
		return crepo.getCustomer(l);
	}
	
	@jakarta.transaction.Transactional
	public Customer registerCustomer(Customer customer) {
	    Login savedLogin = lservice.saveLogin(customer.getLogin_id());
	    customer.setLogin_id(savedLogin);
	    return crepo.save(customer);
	}
	
	

	

	

}
