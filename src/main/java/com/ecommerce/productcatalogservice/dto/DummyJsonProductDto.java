package com.ecommerce.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyJsonProductDto {

    private Long id;
    private String title;
    private String description;
    private Double price;

    private String category;

}
