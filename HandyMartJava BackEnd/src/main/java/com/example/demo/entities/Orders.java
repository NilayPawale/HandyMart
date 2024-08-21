package com.example.demo.entities;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orderId;

    private String trackingNumber;

    private LocalDate orderDate;

    private String deliveryAddress;
    
    

    private LocalDate deliveryDate;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payments payments;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


    private String generateTrackingNumber() {
        // For simplicity, use a UUID or any other generation logic
        return java.util.UUID.randomUUID().toString();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Orders() {
        // Set default values if necessary
        this.trackingNumber = generateTrackingNumber();
        // Optionally set default values for orderDate and deliveryDate
    }
}
