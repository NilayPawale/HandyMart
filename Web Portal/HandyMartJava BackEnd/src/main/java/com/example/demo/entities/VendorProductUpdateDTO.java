package com.example.demo.entities;

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
public class VendorProductUpdateDTO {
	
	 private String productName;       // Updated to match your field
     // Assuming this is the correct field name
  private String description;
  private Float price;
  private int inventory;
  private String subcategory_name;
  private String category_name;
}
