package com.example.demo.ResponseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
	private int cartId;
	private CustomerResponseDto customer;
	private List<CartItemResponseDto> items;
}