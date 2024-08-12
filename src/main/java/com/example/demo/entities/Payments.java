package com.example.demo.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "payment")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payments {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int paymentId;

	    private Float amount;
	    private LocalDateTime paymentDate;
	    private String paymentMethod;
	   
	    private String DeliveryAddress;

	    @OneToOne
	    @JoinColumn(name = "cart_id", nullable = false)
	    private Cart cart;
	    @PrePersist
	    protected void onCreate() {
	        this.paymentDate = LocalDateTime.now();
	    }
}
