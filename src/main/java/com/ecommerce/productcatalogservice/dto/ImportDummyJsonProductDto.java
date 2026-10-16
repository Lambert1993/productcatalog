package com.ecommerce.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImportDummyJsonProductDto {

    private List<DummyJsonProductDto> products;
}
