package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseDTO.OrdersResponseDto;
import com.example.demo.entities.OrderDTO;
import com.example.demo.services.OrderServices;
@CrossOrigin("*")
@RestController
public class OrderController {
	  @Autowired
	    private OrderServices orderService;
	  
	  @PostMapping("/createOrder")
	    public ResponseEntity<OrdersResponseDto> createOrder(@RequestBody OrderDTO orderDTO) {
	        try {
	            OrdersResponseDto responseDto = orderService.createOrder(orderDTO);
	            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	        } catch (IllegalArgumentException e) {
	            // Handle validation errors or entity not found exceptions
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            // Handle other exceptionsa
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	  
	   @GetMapping("/getOrder/{cartId}/{paymentId}")
	    public ResponseEntity<OrdersResponseDto> getOrder(
	            @PathVariable int cartId,
	            @PathVariable int paymentId) {
	        try {
	            OrdersResponseDto responseDto = orderService.getOrderFromCartAndPayment(cartId, paymentId);
	            return new ResponseEntity<>(responseDto, HttpStatus.OK);
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
