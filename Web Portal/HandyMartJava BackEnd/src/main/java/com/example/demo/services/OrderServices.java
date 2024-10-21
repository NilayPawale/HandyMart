package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Mapper.OrderMapper;
import com.example.demo.ResponseDTO.OrdersResponseDto;
import com.example.demo.entities.Cart;
import com.example.demo.entities.OrderDTO;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Payments;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.PaymentRepository;

@Service
@Transactional
public class OrderServices {
	  @Autowired
	    private CartRepository cartRepository;

	    @Autowired
	    private PaymentRepository paymentsRepository;

	    @Autowired
	    private OrderRepository ordersRepository;
	    
	    public  OrdersResponseDto createOrder(OrderDTO orderDTO) {
	    	  if (orderDTO == null) {
	              throw new IllegalArgumentException("OrderDTO must not be null");
	          }
	    	  Cart cart = cartRepository.findById(orderDTO.getCartId()).get(); 

	    	  
	    	  Payments payment = orderDTO.getPaymentId() != 0 
	    	            ? paymentsRepository.findById(orderDTO.getPaymentId())
	    	                .orElseThrow(() -> new IllegalArgumentException("Payment not found for ID: " + orderDTO.getPaymentId()))
	    	            : null;
	    	  Orders order = OrderMapper.toEntity(orderDTO, cartRepository, paymentsRepository);
	          order.setCart(cart);
	          order.setPayments(payment);
	          order.setDeliveryDate(order.getOrderDate().plusDays(6));
	          order.setDeliveryAddress(payment.getDeliveryAddress());
	          Orders savedOrder = ordersRepository.save(order);

	          // Convert saved Orders entity to OrdersResponseDto
	          OrdersResponseDto responseDto = OrderMapper.toResponseDto(savedOrder);

	          // Return the response DTO
	          return responseDto;
	    }
	    
	    public OrdersResponseDto getOrderFromCartAndPayment(int cartid, int paymentid) {
	    	Orders order =  ordersRepository.findByCartIdAndPaymentId(cartid, paymentid);
	    	OrdersResponseDto ordersResponseDto = OrderMapper.toResponseDto(order);
	    	return ordersResponseDto;
	    }
}
