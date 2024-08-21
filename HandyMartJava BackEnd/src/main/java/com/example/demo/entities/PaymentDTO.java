package com.example.demo.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDTO {
	private int cartId;
	private String paymentMethod;
	private String deliveryAddress;

}
