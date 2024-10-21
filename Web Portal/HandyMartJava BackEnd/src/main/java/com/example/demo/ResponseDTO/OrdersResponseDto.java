package com.example.demo.ResponseDTO;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponseDto {
    private int orderId;
    private String trackingNumber;
    private LocalDate orderDate;
    private String deliveryAddress;
    private LocalDate deliveryDate;
    private int cartId; // Assuming you want to include cart ID
    private int paymentId; // Assuming you want to include payment ID
    private String paymentMethod;
    private String customerName;
    
}

