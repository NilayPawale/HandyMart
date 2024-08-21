
package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseDTO.OrdersResponseDto;
import com.example.demo.ResponseDTO.PaymentResponseDTO;
import com.example.demo.entities.OrderDTO;
import com.example.demo.entities.PaymentDTO;
import com.example.demo.services.OrderServices;
import com.example.demo.services.PaymentService;
@CrossOrigin("*")
@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderServices orderServices;
	
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        // Call the service method
    	System.out.println(paymentDTO+"****************");
    	//paymentDTO.setCartId(7);
    	System.out.println(paymentDTO+"****************");
    	System.out.println(paymentDTO.getDeliveryAddress()+"**************************************************");
        PaymentResponseDTO paymentResponseDTO = paymentService.createPayment(paymentDTO);
        // Return the response with status 201 Created
        OrderDTO orderDto = new OrderDTO(paymentDTO.getDeliveryAddress(),paymentResponseDTO.getPaymentId(),paymentDTO.getCartId());
        OrdersResponseDto orderResponseDto = orderServices.createOrder(orderDto);
        return new ResponseEntity<>(paymentResponseDTO, HttpStatus.CREATED);
    }
    
    
    
    

}
