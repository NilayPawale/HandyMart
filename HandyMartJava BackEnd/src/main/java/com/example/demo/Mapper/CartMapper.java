package com.example.demo.Mapper;
import java.util.stream.Collectors;

import com.example.demo.ResponseDTO.CartItemResponseDto;
import com.example.demo.ResponseDTO.CartResponseDto;
import com.example.demo.ResponseDTO.CustomerResponseDto;
import com.example.demo.ResponseDTO.SubProductCategoryResponseDto;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.SubProductCategory;

public class CartMapper {

    public static CartResponseDto toDto(Cart cart) {
        return new CartResponseDto(
            cart.getCartId(),
            toCustomerDto(cart.getCustomer()),
            cart.getItems().stream()
                .map(CartMapper::toItemDto)
                .collect(Collectors.toList())
        );
    }

    private static CartItemResponseDto toItemDto(CartItem item) {
        return new CartItemResponseDto(
            item.getCartItemId(),
            item.getQuantity(),
            toSubProductCategoryDto(item.getCategories()),
            item.getPrice()
        );
    }

    private static CustomerResponseDto toCustomerDto(Customer customer) {
        return new CustomerResponseDto(
            customer.getCustomer_id(),
            customer.getFirstname()
            // Map other fields as needed
        );
    }

    private static SubProductCategoryResponseDto toSubProductCategoryDto(SubProductCategory category) {
        return new SubProductCategoryResponseDto(
            category.getSubcategory_id(),
            category.getSubcategory_name()
            // Map other fields as needed
        );
    }
}

