package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Payments;

public interface PaymentRepository extends JpaRepository<Payments, Integer>{

}
