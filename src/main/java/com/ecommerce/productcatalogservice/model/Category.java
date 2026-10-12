package com.ecommerce.productcatalogservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends  BaseEntity{

    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
