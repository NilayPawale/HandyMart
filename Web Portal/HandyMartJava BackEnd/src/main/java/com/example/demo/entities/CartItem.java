package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int cartItemId;
private int quantity;
@ManyToOne
@JoinColumn(name = "sub_product_category_id")
private SubProductCategory categories;
private Float price;

@ManyToOne
@JoinColumn(name = "cartId")
private Cart cart;
}
