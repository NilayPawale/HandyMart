package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Login;
import com.example.demo.entities.ProductCategory;
import com.example.demo.entities.SubProductCategory;
import com.example.demo.entities.Vendor;
import com.example.demo.entities.VendorProduct;
import com.example.demo.entities.VendorProductDummy;
import com.example.demo.entities.VendorProductUpdateClass;
import com.example.demo.entities.VendorProductUpdateDTO;
import com.example.demo.repositories.ProductCategoryRepository;
import com.example.demo.repositories.SubProductCategoryRepository;
import com.example.demo.repositories.VendorProductRepository;
import com.example.demo.repositories.VendorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VendorProductService {
	@Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SubProductCategoryRepository subProductCategoryRepository;

    @Autowired
    private VendorProductRepository vendorProductRepository;
    
    @Autowired
    VendorService vservice;
    @Autowired
    SubProductCategoryService sbservice;
    
    @Autowired
    ProductCategoryService pcservice;
    
    @Autowired
    VendorRepository vrepo;
    
//    @Transactional
//	public VendorProduct registerVendorProduct(VendorProduct vp) {
//	   // Login savedLogin = lservice.saveLogin(vendor.getLogin_id());
//    	SubProductCategory sb=sbservice.saveSubProduct(vp.getSubProductCategory());
//	    vp.setSubProductCategory(sb);
//	    return productCategoryRepository.save(vp);
//	}
    
    @Transactional
    public VendorProduct registerVendorProduct(VendorProduct vp) {
        SubProductCategory sb = sbservice.saveSubProduct(vp.getSubProductCategory());
        vp.setSubProductCategory(sb);
        return vendorProductRepository.save(vp);
    }

    public List<VendorProduct> getAllVendorProducts() {
        return vendorProductRepository.findAll();
    }
    
    public List<VendorProduct> getProductsByVendorId(int vendorId) {
        return vendorProductRepository.findProductsByVendorId(vendorId);
    }
  public VendorProductUpdateDTO getVendorProductId( int id) {
	    VendorProduct vendorProduct = vendorProductRepository.findById(id).get();
	    VendorProductUpdateDTO updateDTO = new VendorProductUpdateDTO();
	    updateDTO.setSubcategory_name(vendorProduct.getSubProductCategory().getSubcategory_name());
	    updateDTO.setCategory_name(vendorProduct.getSubProductCategory().getProductCategory().getCategory_name());
	    updateDTO.setDescription(vendorProduct.getDescription());
	    updateDTO.setPrice(vendorProduct.getPrice());
	    updateDTO.setInventory(vendorProduct.getInventory());
	    updateDTO.setProductName(vendorProduct.getProductname());
	   return updateDTO;
  }
    
//    public VendorProduct regVendorProduct(VendorProductDummy vreg, int loginId) {
//        System.out.println("In Vendor Method");
//        try {
//            System.out.println(vreg);
//            ProductCategory pc = pcservice.getproductcat(vreg.getProductcategory_id());
//            SubProductCategory sb = new SubProductCategory(pc, vreg.getSubproductname());
//
//            SubProductCategory sbsaved = sbservice.saveSubProduct(sb);
//
//            // Fetch vendor_id based on login_id using the repository query
//            Integer vendorId = vrepo.findVendorIdByLoginId(loginId);
//
//            // Create a new Vendor instance with the retrieved vendor_id
//            Vendor v = new Vendor(vendorId);
//
//            VendorProduct pro = new VendorProduct(v, sb, vreg.getProductname(), vreg.getDescription(), vreg.getPrice(), vreg.getInventory(), true);
//            return vendorProductRepository.registerVendorProduct(pro);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }

    
    public VendorProduct regVendorProduct(VendorProductDummy vreg, int loginId) {
        System.out.println("In Vendor Method");
        try {
            System.out.println(vreg);
            ProductCategory pc = pcservice.getproductcat(vreg.getProductcategory_id());
            SubProductCategory sb = new SubProductCategory(pc, vreg.getSubproductname());

            SubProductCategory sbsaved = sbservice.saveSubProduct(sb);

            // Fetch vendor_id based on login_id using the repository query
            Integer vendorId = vrepo.findVendorIdByLoginId(loginId);

            // Create a new Vendor instance with the retrieved vendor_id
            Vendor v = new Vendor(vendorId, null, null, null, null, null, null, null, null);

            VendorProduct pro = new VendorProduct(v, sb, vreg.getProductname(), vreg.getDescription(), vreg.getPrice(), vreg.getInventory(), true);
            return vendorProductRepository.save(pro);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void deleteProductById(int productId) {
        vendorProductRepository.deleteById(productId);
    }
    
    public void updateVendorProduct(VendorProductUpdateClass productUpdateDTO) {
        // Retrieve vendor products based on the provided ID
        List<VendorProduct> vendorProducts = vendorProductRepository.getVendorProductId(productUpdateDTO.getId());
        
        if (vendorProducts.isEmpty()) {
            throw new EntityNotFoundException("No vendor product found with ID: " + productUpdateDTO.getId());
        }
        
        // Get the first element from the list
        VendorProduct vendorProduct = vendorProducts.get(0);

        // Update the fields of the first VendorProduct
        vendorProduct.setDescription(productUpdateDTO.getDescription());
        vendorProduct.setPrice(productUpdateDTO.getPrice());
        vendorProduct.setInventory(productUpdateDTO.getInventory());
        vendorProduct.setProductname(productUpdateDTO.getProductName());

        // Ensure subProductCategory and productCategory are not null before setting properties
        if (vendorProduct.getSubProductCategory() != null) {
            vendorProduct.getSubProductCategory().setSubcategory_name(productUpdateDTO.getCategoryName());
            
            if (vendorProduct.getSubProductCategory().getProductCategory() != null) {
            	vendorProduct.getSubProductCategory().setSubcategory_name(productUpdateDTO.getCategoryName());
            } else {
                throw new IllegalStateException("ProductCategory is null for vendor product ID: " + vendorProduct.getVendor().getVendor_id());
            }
        } else {
            throw new IllegalStateException("SubProductCategory is null for vendor product ID: " +vendorProduct.getVendor().getVendor_id());
        }
        
        // Save the updated VendorProduct
        vendorProductRepository.save(vendorProduct);
    }



}
