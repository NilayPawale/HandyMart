package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ProductCategory;
import com.example.demo.entities.Vendor;
import com.example.demo.services.ProductCategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductCategoryController {
	
	@Autowired
	 ProductCategoryService productCategoryService;

	@GetMapping("/getallproductcategories")
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> vendors = productCategoryService.getAllProductCategories();
        return ResponseEntity.ok().body(vendors);
    }
	@GetMapping("/getproductcategory")
	public ResponseEntity<ProductCategory>getProductCategory(@RequestParam("category_id")int category_id){
		ProductCategory category = productCategoryService.getproductcat(category_id);
		return ResponseEntity.ok().body(category);
		
	}
}
