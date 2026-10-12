package com.ecommerce.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String name;

    private String description;

    private Double price;

    private CategoryDto category;
}
