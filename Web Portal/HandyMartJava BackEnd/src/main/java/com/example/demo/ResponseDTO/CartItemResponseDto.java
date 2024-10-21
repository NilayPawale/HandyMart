package com.example.demo.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {
    private int cartItemId;
    private int quantity;
    private SubProductCategoryResponseDto categories;
    private Float price;
}