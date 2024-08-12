package com.example.demo.ResponseDTO;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentResponseDTO {
	 private int paymentId;
	    private Float amount;
	    private LocalDateTime paymentDate;
	    private String paymentMethod;
	    private int cartId;
	  
	    private String DeliveryAddress;
	    // ID to link to the Cart entity
}

