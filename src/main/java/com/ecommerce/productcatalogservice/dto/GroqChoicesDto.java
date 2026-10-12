package com.ecommerce.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroqChoicesDto {

    public Integer index;
    public GroqMessage message;
}
