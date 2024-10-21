package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

	
	
	@Query("SELECT c FROM Cart c WHERE c.customer.customer_id = :customerId")
	Cart findCartByUserId(@Param("customerId") int customerId);

	
	@Query("SELECT c.customer.id FROM Cart c WHERE c.cartId = :cartId")
    Integer findCustomerIdByCartId(@Param("cartId") int cartId);
	
	
}
