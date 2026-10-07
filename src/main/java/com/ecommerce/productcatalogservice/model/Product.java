package com.ecommerce.productcatalogservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseEntity{

    private String name;
    private Double price;
    private String description;
    private Category category;
}
