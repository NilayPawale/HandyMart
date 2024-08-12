package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseDTO.CartResponseDto;
import com.example.demo.entities.CartDTO;
import com.example.demo.entities.CartDTO;
import com.example.demo.entities.Customer;
import com.example.demo.services.CartService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.SubProductCategoryService;



@RestController
public class CartController {

	@Autowired
private	CartService cartService;
	
	@Autowired
private	CustomerService customerService;
	
	@Autowired
private	SubProductCategoryService subProductCategoryService;
	
	@PostMapping("/addcart")
	public ResponseEntity<String>addToCart(@RequestBody CartDTO cartDTO){
		System.out.println(cartDTO.getCustomerId()+" "+ cartDTO.getQuantity()+" "+cartDTO.getSubproductId());
		cartService.addToCart(cartDTO);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/getCart/{id}")
	public ResponseEntity<CartResponseDto> getCartById(@PathVariable("id") int id) {
	    System.out.println("Received ID: " + id);
	    CartResponseDto cartResponseDto = cartService.getCartById(id);
	    return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
	}


	
}
