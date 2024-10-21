package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendorproducts")
public class VendorProduct {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vendor_product_id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubProductCategory subProductCategory;

    @Column(nullable = false)
    private String productname;

    @Column
    private String description;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private int inventory;

    @Column(nullable = false)
    private boolean active_flag;

	public VendorProduct() {
		super();
	}

	public VendorProduct( Vendor vendor, SubProductCategory subProductCategory,
			String productname, String description, Float price, int inventory, boolean active_flag) {
		super();
		this.vendor = vendor;
		this.subProductCategory = subProductCategory;
		this.productname = productname;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.active_flag = active_flag;
	}

	public int getVendor_product_id() {
		return vendor_product_id;
	}

	public void setVendor_product_id(int vendor_product_id) {
		this.vendor_product_id = vendor_product_id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public SubProductCategory getSubProductCategory() {
		return subProductCategory;
	}

	public void setSubProductCategory(SubProductCategory subProductCategory) {
		this.subProductCategory = subProductCategory;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public boolean isActive_flag() {
		return active_flag;
	}

	public void setActive_flag(boolean active_flag) {
		this.active_flag = active_flag;
	}

	@Override
	public String toString() {
		return "VendorProduct [vendor_product_id=" + vendor_product_id + ", vendor=" + vendor + ", subProductCategory="
				+ subProductCategory + ", productname=" + productname + ", description=" + description + ", price="
				+ price + ", inventory=" + inventory + ", active_flag=" + active_flag + "]";
	}
    
}
