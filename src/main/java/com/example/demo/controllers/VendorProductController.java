package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Login;
import com.example.demo.entities.ProductCategory;
import com.example.demo.entities.Role;
import com.example.demo.entities.SubProductCategory;
import com.example.demo.entities.Vendor;
import com.example.demo.entities.VendorProduct;
import com.example.demo.entities.VendorProductDummy;
import com.example.demo.entities.VendorReg;
import com.example.demo.services.LoginService;
import com.example.demo.services.ProductCategoryService;
import com.example.demo.services.SubProductCategoryService;
import com.example.demo.services.VendorProductService;
import com.example.demo.services.VendorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

//@RequestMapping("/api/vendor")
public class VendorProductController {
	@Autowired
    VendorProductService productService;
	
	@Autowired
	ProductCategoryService pcservice;
	@Autowired
	SubProductCategoryService sbservice;
	
	@Autowired
	VendorService vservice;
	
	@Autowired
	LoginService lservice;
	
//	@PostMapping("/save")
//    public ResponseEntity<String> saveVendorProduct(@RequestBody VendorProductDummy vendorProductDTO) {
//        productService.saveVendorProduct(vendorProductDTO);
//        return ResponseEntity.ok("Vendor Product saved successfully");
//    }
	
	
//	
	
	@PostMapping("/addproduct")
	public VendorProduct regVendorProduct(@RequestBody VendorProductDummy vreg)
	{
		System.out.println("In Vendor Method");
		try {
			System.out.println(vreg);
		ProductCategory pc=pcservice.getproductcat(vreg.getProductcategory_id());
		SubProductCategory sb=new SubProductCategory(pc,vreg.getSubproductname());
		
		SubProductCategory sbsaved=sbservice.saveSubProduct(sb);
		 //Vendor v =new Vendor(2);
	  // Vendor v =new Vendor(vendor_id);
		Login l=lservice.getById(vreg.getLogin_id());
		System.out.println(l);
		Vendor v= vservice.getVendor(l);
		System.out.println(v);
	   VendorProduct pro=new VendorProduct(v,sb,vreg.getProductname(),vreg.getDescription(),vreg.getPrice(),vreg.getInventory(),true);
	   return productService.registerVendorProduct(pro);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	@GetMapping("/getallProducts")
    public ResponseEntity<List<VendorProduct>> getAllProducts() {
        List<VendorProduct> products = productService.getAllVendorProducts();
        return ResponseEntity.ok().body(products);
    }
	

	
	@GetMapping("/byVendor")
	public ResponseEntity<List<VendorProduct>> getProductsByVendorId(@RequestParam("login_id") int login_id) {
	    int vendorId = vservice.getVendorIdByLoginId(login_id);
	    List<VendorProduct> products = productService.getProductsByVendorId(vendorId);
	    return ResponseEntity.ok().body(products);
	}


	
	@DeleteMapping("/byVendor/{vendor_product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("vendor_product_id") int vendor_product_id) {
		productService.deleteProductById(vendor_product_id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
