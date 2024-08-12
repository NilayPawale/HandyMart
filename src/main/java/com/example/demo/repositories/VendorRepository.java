package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Login;
import com.example.demo.entities.Vendor;

import jakarta.transaction.Transactional;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	
	@Query("select v from Vendor v where login_id= :l")
	public Vendor getVendor(Login l);
	
	@Query(value = "INSERT INTO vendors (FirstName, LastName, Email, Phone,AadharNumber,skillset, login_id) VALUES (:firstName, :lastName, :email, :phone, :aadharnumber, :skillset, :loginId)", nativeQuery = true)
    @Transactional
    @Modifying
    void insertVendor(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("phone") String phone, @Param("aadharnumber") String aadharnumber,@Param("skillset") String skillset, @Param("loginId") int loginId);

	@Query("SELECT v FROM Vendor v WHERE v.login_id.login_id = :login_id")
    Vendor findByLoginId(@Param("login_id") int login_id);
	
	@Query("SELECT v.vendor_id FROM Vendor v WHERE v.login_id = :loginId")
    Integer findVendorIdByLoginId(@Param("loginId") Integer loginId);

}
