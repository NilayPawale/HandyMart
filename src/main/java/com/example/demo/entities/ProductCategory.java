package com.example.demo.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productcategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
	@Id
    private int category_id;

    @Column
    private String category_name;

    @Column
    private boolean active_flag;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SubProductCategory> subProductCategories;

}
