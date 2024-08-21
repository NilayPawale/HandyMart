package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entities.VendorProduct;

public interface VendorProductRepository extends JpaRepository<VendorProduct, Integer> {
	@Query("SELECT vp FROM VendorProduct vp WHERE vp.vendor.vendor_id = :vendor_id")
    List<VendorProduct> findProductsByVendorId(@Param("vendor_id") int vendor_id);

//	VendorProduct registerVendorProduct(VendorProduct pro);
	
	  @Query("SELECT vp FROM VendorProduct vp WHERE vp.subProductCategory.subcategory_id = :subcategoryId")
	    List<VendorProduct> findVendorProductsBySubProductCategoryId(@Param("subcategoryId") int subcategoryId);
	  
	  
	  @Query("SELECT spc FROM VendorProduct spc WHERE spc.productname LIKE %:keyword%")
	  List<VendorProduct> findByKeyword(@Param("keyword") String keyword);
	  
	  @Query("SELECT spc FROM VendorProduct spc WHERE spc.vendor_product_id =:vendor_product_id")
	  List<VendorProduct> getVendorProductId(@Param("vendor_product_id") int vendor_product_id);

}
