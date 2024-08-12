package com.example.demo.Mapper;

import com.example.demo.ResponseDTO.PaymentResponseDTO;
import com.example.demo.entities.Cart;
import com.example.demo.entities.PaymentDTO;
import com.example.demo.entities.Payments;
import com.example.demo.repositories.CartRepository;

public class PaymentMapper {

	 public static PaymentResponseDTO toResponseDto(Payments payment) {
	        if (payment == null) {
	            return null;
	        }
	        return new PaymentResponseDTO(
	            payment.getPaymentId(),
	            payment.getAmount(),
	            payment.getPaymentDate(),
	            payment.getPaymentMethod(),
	            payment.getCart().getCartId(),
	            payment.getDeliveryAddress()
	            // Assuming cart is not null
	        );
	    }

	    // Convert PaymentDTO to Payments entity
	    public static Payments toEntity(PaymentDTO paymentDTO, CartRepository cartRepository) {
	        if (paymentDTO == null) {
	            return null;
	        }
	        Payments payment = new Payments();
	        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
	        
	        // Fetch the Cart entity based on the cart ID from the DTO
	        Cart cart = cartRepository.findById(paymentDTO.getCartId())
	                                  .orElseThrow(() -> new IllegalArgumentException("Cart not found for ID: " + paymentDTO.getCartId()));
	        payment.setCart(cart);

	        // Amount and paymentDate will be set in the service method
	        return payment;
	    }
}

