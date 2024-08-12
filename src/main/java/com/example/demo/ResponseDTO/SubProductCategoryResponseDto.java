package com.example.demo.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubProductCategoryResponseDto {
    private int subProductCategoryId;
    private String categoryName;
    // Add other sub-product category fields as necessary
}