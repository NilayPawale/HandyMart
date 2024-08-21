package com.example.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseDTO.CartResponseDto;
import com.example.demo.entities.CartDTO;
import com.example.demo.entities.VendorProduct;
import com.example.demo.exception_handling.CartNotFoundException;

import com.example.demo.exception_handling.ProductNotFoundException;
import com.example.demo.services.CartService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.SubProductCategoryService;

@CrossOrigin("*")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SubProductCategoryService subProductCategoryService;

    @PostMapping("/addcart")
    public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) {
        System.out.println(cartDTO.getCustomerId() + " " + cartDTO.getQuantity() + " " + cartDTO.getSubproductId());
        cartService.addToCart(cartDTO);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/getCart/{id}")
    public ResponseEntity<CartResponseDto> getCartById(@PathVariable("id") int id) {
        System.out.println("Received ID: " + id);
        CartResponseDto cartResponseDto = cartService.getCartById(id);
        if (cartResponseDto == null) {
            throw new CartNotFoundException("Cart not found for ID: " + id);
        }
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @GetMapping("/searchByName/{name}/{customerId}")
    public ResponseEntity<?> searchProductsByName(@PathVariable("name") String name, @PathVariable("customerId") int customerId) {
      
        List<VendorProduct> products = cartService.searchProductsByName(name, customerId);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with the name: " + name);
        }
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/getCartid/{id}")
    public ResponseEntity<?> getCartId(@PathVariable("id") int customerId) {
        int cartId = cartService.getCartId(customerId);
        if (cartId == 0) {
            throw new CartNotFoundException("Cart not found for customer ID: " + customerId);
        }
        return ResponseEntity.ok().body(cartId);
    }

    @DeleteMapping("/{customerId}/items/{subProductId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable int customerId, @PathVariable int subProductId) {
        try {
            cartService.removeFromCart(customerId, subProductId);
            return ResponseEntity.ok("Item removed successfully from cart.");
        } catch (CartNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Failed to remove item: " + e.getMessage());
        }
    }
}
