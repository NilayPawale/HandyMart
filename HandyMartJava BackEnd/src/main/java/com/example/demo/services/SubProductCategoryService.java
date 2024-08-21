package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Login;
import com.example.demo.entities.SubProductCategory;
import com.example.demo.repositories.SubProductCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@org.springframework.transaction.annotation.Transactional
public class SubProductCategoryService {
@Autowired
private SubProductCategoryRepository subProductCategoryRepository;

public SubProductCategory getSubProductCategory(int id) {
	SubProductCategory category = subProductCategoryRepository.findById(id).get();
	return category;
}






 SubProductCategoryRepository sbr;
 
 @Autowired
 public void YourServiceClass(SubProductCategoryRepository sbr) {
     this.sbr = sbr;
 }
 @Transactional
 public SubProductCategory saveSubProduct(SubProductCategory sb) {
     try {
         return sbr.save(sb);
     } catch (Exception e) {
         e.printStackTrace(); // Log the exception stack trace
         throw new RuntimeException("Error saving Sub product information.", e);
     }
 }
	
}
