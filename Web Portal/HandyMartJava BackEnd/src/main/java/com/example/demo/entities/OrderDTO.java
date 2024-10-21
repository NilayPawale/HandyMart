package com.example.demo.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    
    private String deliveryAddress;
    private int paymentId; // or another representation if needed
    private int cartId; // or another representation if needed

    
}

