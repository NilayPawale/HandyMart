package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class CartDTO1 {
	private int quantity;
	private int customerId;
	private int subproductId;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSubproductId() {
		return subproductId;
	}
	public void setSubproductId(int subproductId) {
		this.subproductId = subproductId;
	}
	public CartDTO1() {
		super();
	}
	
}
