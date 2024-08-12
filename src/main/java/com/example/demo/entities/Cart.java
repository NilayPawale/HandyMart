package com.example.demo.entities;





import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartId;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	  @OneToMany(cascade = CascadeType.ALL) // Cascade to allow saving items with the cart
	   @JoinColumn(name = "cart_id") // Foreign key to link CartItem to Cart
	   private List<CartItem> items; // List of CartItem objects
}
