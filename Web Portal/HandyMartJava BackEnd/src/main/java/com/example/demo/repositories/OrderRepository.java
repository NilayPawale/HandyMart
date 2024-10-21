package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

	 @Query("SELECT o FROM Orders o WHERE o.cart.cartId = :cart_id AND o.payments.paymentId = :payment_id")
	    Orders findByCartIdAndPaymentId(@Param("cart_id") int cartId, @Param("payment_id") int paymentId);
}
