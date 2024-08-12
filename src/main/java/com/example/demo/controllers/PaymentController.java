package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseDTO.PaymentResponseDTO;
import com.example.demo.entities.PaymentDTO;
import com.example.demo.services.PaymentService;

@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        // Call the service method
        PaymentResponseDTO paymentResponseDTO = paymentService.createPayment(paymentDTO);
        // Return the response with status 201 Created
        return new ResponseEntity<>(paymentResponseDTO, HttpStatus.CREATED);
    }

}
