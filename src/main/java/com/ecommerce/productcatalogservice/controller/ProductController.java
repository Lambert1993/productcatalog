package com.ecommerce.productcatalogservice.controller;

import com.ecommerce.productcatalogservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    List<Product> getAllProducts(){
        return List.of();
    }
}
