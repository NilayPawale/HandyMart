package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ProductCategory;
import com.example.demo.entities.Role;
import com.example.demo.entities.Vendor;
import com.example.demo.repositories.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	
	@Autowired
	ProductCategoryRepository pcrepo;
	
	
	
	public ProductCategory getproductcat(int id)
	 {
		 return pcrepo.findById(id).get();
	 }
	
	public List<ProductCategory> getAllProductCategories() {
        return pcrepo.findAll();
    }
}
