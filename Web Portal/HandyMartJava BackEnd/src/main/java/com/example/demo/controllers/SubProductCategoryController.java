package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.SubProductCategory;
import com.example.demo.services.SubProductCategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SubProductCategoryController {
	
@Autowired
private SubProductCategoryService subproductcategory ;

@GetMapping("/getsubproductcategory")
public ResponseEntity<SubProductCategory>getSubProCategory(@RequestParam("subcategory_id")int subcategory_id ){
	SubProductCategory category = subproductcategory.getSubProductCategory(subcategory_id);
	return ResponseEntity.ok().body(category);
}


}
