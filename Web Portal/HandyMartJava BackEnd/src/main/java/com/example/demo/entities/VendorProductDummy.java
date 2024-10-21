package com.example.demo.entities;

public class VendorProductDummy {
	private int login_id;
	private int productcategory_id;
    private int subcategory_id;
    private String productname;
    private String subproductname;
    private String description;
    private Float price;
    private int inventory;
    private boolean active_flag;
    
	public VendorProductDummy() {
		super();
	}
	public VendorProductDummy(int login_id, int subcategory_id, String productname, String subproductname,
			String description, Float price, int inventory, boolean active_flag) {
		super();
		this.login_id = login_id;
		this.subcategory_id = subcategory_id;
		this.productname = productname;
		this.subproductname = subproductname;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.active_flag = active_flag;
	}
	
	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getSubproductname() {
		return subproductname;
	}
	public void setSubproductname(String subproductname) {
		this.subproductname = subproductname;
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
		return "VendorProductDummy [login_id=" + login_id + ", productcategory_id=" + productcategory_id
				+ ", subcategory_id=" + subcategory_id + ", productname=" + productname + ", subproductname="
				+ subproductname + ", description=" + description + ", price=" + price + ", inventory=" + inventory
				+ ", active_flag=" + active_flag + "]";
	}
	public int getProductcategory_id() {
		return productcategory_id;
	}
	public void setProductcategory_id(int productcategory_id) {
		this.productcategory_id = productcategory_id;
	}
	
	
    
    
}
