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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Payments> payments;
    
    // Add convenience methods for bi-directional relationship management
    public void addCartItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeCartItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }

    public void addPayment(Payments payment) {
        payments.add(payment);
        payment.setCart(this);
    }

    public void removePayment(Payments payment) {
        payments.remove(payment);
        payment.setCart(null);
    }
}
