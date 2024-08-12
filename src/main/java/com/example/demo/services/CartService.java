package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Mapper.CartMapper;
import com.example.demo.ResponseDTO.CartResponseDto;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartDTO;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.SubProductCategory;
import com.example.demo.entities.VendorProduct;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.SubProductCategoryRepository;
import com.example.demo.repositories.VendorProductRepository;

@Service
@Transactional
public class CartService {
	
	@Autowired
	CustomerRepository crepo;
	
     @Autowired
     LoginService lservice;
 	
 	@Autowired
 	CartRepository cartRepository;
 	
 	@Autowired
 	VendorProductRepository vendorProductRepository;
 	
 	@Autowired
 	SubProductCategoryRepository subProductCategoryRepository;

	public CartResponseDto getCartById(int id) {
		System.out.println(id+"********");
		Cart cart = cartRepository.findCartByUserId(id);
		System.out.println(cart+" ***********************" );
		CartResponseDto cartResponseDto = CartMapper.toDto(cart);
        return cartResponseDto;
		
	}
     
	public void addToCart(CartDTO cartDTO) {
	    System.out.println(cartDTO.getCustomerId() + " " + cartDTO.getQuantity() + " " + cartDTO.getSubproductId());

	    // Fetch the cart based on customer ID
	    Cart cart = cartRepository.findCartByUserId(cartDTO.getCustomerId());
	    Customer customer = crepo.findByCustomerIds(cartDTO.getCustomerId());

	    // If the cart does not exist, create a new one
	    if (cart == null) {
	        cart = new Cart();
	        cart.setCustomer(customer);
	        cart.setItems(new ArrayList<>()); // Initialize the items list
	        cartRepository.save(cart); // Save the new cart
	    }

	    // Check if the cart item already exists
	    Optional<CartItem> cartItem = cart.getItems().stream()
	            .filter(item -> item.getCategories() != null && item.getCategories().getSubcategory_id() == cartDTO.getSubproductId())
	            .findFirst();

	    int subProductId = cartDTO.getSubproductId();
	    List<VendorProduct> vendorProductList = vendorProductRepository.findVendorProductsBySubProductCategoryId(subProductId);

	    if (vendorProductList.isEmpty()) {
	        throw new IllegalArgumentException("VendorProduct not found for SubProductCategory ID: " + subProductId);
	    }

	    Float price = vendorProductList.get(0).getPrice();

	    // If the item does not exist, create a new CartItem
	    if (cartItem.isEmpty()) {
	        // Safely fetch the SubProductCategory
	        Optional<SubProductCategory> subOptional = subProductCategoryRepository.findBySubcategoryId(subProductId);

	        if (subOptional.isPresent()) {
	            // Create a new CartItem if the SubProductCategory is found
	            CartItem item = new CartItem();
	            item.setCategories(subOptional.get());
	            item.setQuantity(cartDTO.getQuantity()); // Use quantity from CartDTO
	            item.setPrice(price*cartDTO.getQuantity()); // Set initial price
	            cart.getItems().add(item); // Add the new item to the cart
	        } else {
	            // Handle the case where the SubProductCategory is not found
	            throw new IllegalArgumentException("SubProductCategory not found for ID: " + subProductId);
	        }
	    } else {
	        // If the item already exists, increase the quantity and update the price
	        CartItem existingItem = cartItem.get();
	        existingItem.setQuantity(existingItem.getQuantity() + cartDTO.getQuantity()); // Use quantity from CartDTO
	        existingItem.setPrice(existingItem.getQuantity() * price); // Update price based on new quantity
	    }

	    // Save the updated cart to the database
	    cartRepository.save(cart);
	}
 
	
}
