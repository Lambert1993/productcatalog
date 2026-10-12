package com.ecommerce.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroqMessage {

    public String role;
    public String content;
    public String reasoning;
}
