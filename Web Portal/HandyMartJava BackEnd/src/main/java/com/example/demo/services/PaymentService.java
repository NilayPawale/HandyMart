package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Mapper.PaymentMapper;
import com.example.demo.ResponseDTO.OrdersResponseDto;
import com.example.demo.ResponseDTO.PaymentResponseDTO;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Customer;
import com.example.demo.entities.PaymentDTO;
import com.example.demo.entities.Payments;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PaymentRepository;

@Service
@Transactional
public class PaymentService {
	 @Autowired
	    private PaymentRepository paymentRepository;
	
	@Autowired
    private CartRepository cartRepository;
	public  PaymentResponseDTO createPayment(PaymentDTO paymentDTO) {
		
		Payments payment = PaymentMapper.toEntity(paymentDTO, cartRepository);
		
		 if (payment.getCart() == null || payment.getCart().getItems() == null) {
	            throw new IllegalArgumentException("Cart or Cart Items are not available");
	        }
		 Float amt = payment.getCart().getItems().stream()
	                .map(item -> item.getPrice() != null ? item.getPrice() : 0f)
	                .reduce(0f, Float::sum);
		payment.setAmount(amt);
		Cart cart = payment.getCart();
		cart.addPayment(payment);
		payment.setDeliveryAddress(paymentDTO.getDeliveryAddress());
		Payments savedPayments = paymentRepository.save(payment);
		PaymentResponseDTO paymentResponseDTO =  PaymentMapper.toResponseDto(savedPayments);
		return paymentResponseDTO;
	}
	
	
	
	

}
