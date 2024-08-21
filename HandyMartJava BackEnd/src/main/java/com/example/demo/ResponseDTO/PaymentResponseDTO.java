package com.example.demo.ResponseDTO;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class PaymentResponseDTO {
    
		private int paymentId;
	    private Float amount;
	    private LocalDateTime paymentDate;
	    private String paymentMethod;
	    private int cartId;
	    private String deliveryAddress;
		 
	    // ID to link to the Cart entity
}

