
package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.SubProductCategory;

public interface SubProductCategoryRepository extends JpaRepository<SubProductCategory, Integer> {
	@Query("FROM SubProductCategory spc WHERE spc.subcategory_id = :id")
	Optional<SubProductCategory> findBySubcategoryId(@Param("id") int id);


}