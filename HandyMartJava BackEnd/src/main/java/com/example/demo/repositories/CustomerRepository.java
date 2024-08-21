package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Login;

import jakarta.transaction.Transactional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
       
	@Query("select c from Customer c where login_id= :l")
	public Customer getCustomer(Login l);
	
	
	@Query("SELECT c FROM Customer c WHERE c.customer_id= :customerId")
    Customer findByCustomerIds(@Param("customerId") int customerId);
	
	@Query("SELECT c FROM Customer c WHERE c.login_id.login_id= :loginId")
    Customer findByCustomerByLoginID(@Param("loginId") int loginId);
	
	@Query(value = "INSERT INTO customers (FirstName, LastName, Email, Phone, Address, login_id) VALUES (:firstName, :lastName, :email, :phone, :address, :loginId)", nativeQuery = true)
    @Transactional
    @Modifying
    void insertCustomer(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("phone") String phone, @Param("address") String address, @Param("loginId") int loginId);
	
	@Query("SELECT c FROM Customer c WHERE c.login_id.username = :username")
	Optional<Customer> findByUsername(@Param("username") String username);
}
