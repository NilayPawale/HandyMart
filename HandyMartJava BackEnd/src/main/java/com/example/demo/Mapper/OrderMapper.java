package com.example.demo.Mapper;



import com.example.demo.ResponseDTO.OrdersResponseDto;
import com.example.demo.entities.Cart;
import com.example.demo.entities.OrderDTO;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Payments;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.PaymentRepository;


public class OrderMapper {

    // Convert Orders entity to OrdersResponseDto
    public static OrdersResponseDto toResponseDto(Orders order) {
        if (order == null) {
            return null;
        }
        return new OrdersResponseDto(
            order.getOrderId(),
            order.getTrackingNumber(),
            order.getOrderDate(),
            order.getDeliveryAddress(),
            order.getDeliveryDate(),
            order.getCart() != null ? order.getCart().getCartId() : 0,
            order.getPayments() != null ? order.getPayments().getPaymentId() : 0,
            order.getPayments().getPaymentMethod(),
            order.getCart().getCustomer().getFirstname() + " " +order.getCart().getCustomer().getLastname()
        );
    }

    // Convert OrderDTO to Orders entity
    public static Orders toEntity(OrderDTO orderDTO, CartRepository cartRepository, PaymentRepository paymentsRepository) {
        if (orderDTO == null) {
            return null;
        }
        Orders order = new Orders();
        
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
       
        // Generate tracking number and set delivery date in the entity
        // This will be handled in the Orders entity's PostLoad method
       

        // Fetch the Cart entity based on the cart ID from the DTO
        Cart cart = cartRepository.findById(orderDTO.getCartId())
                                  .orElseThrow(() -> new IllegalArgumentException("Cart not found for ID: " + orderDTO.getCartId()));
        order.setCart(cart);

        // Fetch the Payments entity based on the payment ID from the DTO, if needed
        if (orderDTO.getPaymentId() != 0) {
            Payments payment = paymentsRepository.findById(orderDTO.getPaymentId())
                                                 .orElseThrow(() -> new IllegalArgumentException("Payment not found for ID: " + orderDTO.getPaymentId()));
            order.setPayments(payment);
            order.setOrderDate(payment.getPaymentDate().toLocalDate());
        }

        return order;
    }
}

