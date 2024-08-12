package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "subproductcategory")
public class SubProductCategory {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int subcategory_id;

	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private ProductCategory productCategory;

	    @Column
	    private String subcategory_name;

		public SubProductCategory() {
			super();
		}

		public SubProductCategory( ProductCategory productCategory, String subcategory_name) {
			super();
			this.productCategory = productCategory;
			this.subcategory_name = subcategory_name;
		}

		public int getSubcategory_id() {
			return subcategory_id;
		}

		public void setSubcategory_id(int subcategory_id) {
			this.subcategory_id = subcategory_id;
		}

		public ProductCategory getProductCategory() {
			return productCategory;
		}

		public void setProductCategory(ProductCategory productCategory) {
			this.productCategory = productCategory;
		}

		public String getSubcategory_name() {
			return subcategory_name;
		}

		public void setSubcategory_name(String subcategory_name) {
			this.subcategory_name = subcategory_name;
		}

		@Override
		public String toString() {
			return "SubProductCategory [subcategory_id=" + subcategory_id + ", productCategory=" + productCategory
					+ ", subcategory_name=" + subcategory_name + "]";
		}
	    
	    
	
    
}
