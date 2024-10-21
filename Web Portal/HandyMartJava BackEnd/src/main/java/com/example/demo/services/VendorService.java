package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Login;
import com.example.demo.entities.Vendor;
import com.example.demo.repositories.VendorRepository;

import jakarta.transaction.Transactional;

@Service
public class VendorService {
	@Autowired
	VendorRepository vrepo;
	@Autowired
    LoginService lservice;
	
	public Vendor getVendor(Login l) {
		return vrepo.getVendor(l);
	}
	@Transactional
	public Vendor registerVendor(Vendor vendor) {
	    Login savedLogin = lservice.saveLogin(vendor.getLogin_id());
	    vendor.setLogin_id(savedLogin);
	    return vrepo.save(vendor);
	}
	
	public List<Vendor> getAllVendors() {
        return vrepo.findAll();
    }

	
	public Vendor getvendor(int id)
    {
	 return vrepo.findById(id).get();
    }
	
	
	

    public int getVendorIdByLoginId(int login_id) {
        Vendor vendor = vrepo.findByLoginId(login_id);
        if (vendor != null) {
            return vendor.getVendor_id();
        } else {
            throw new RuntimeException("Vendor not found for login_id: " + login_id);
        }
    }

}
