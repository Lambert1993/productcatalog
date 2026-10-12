package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.model.Product;
import org.springframework.stereotype.Service;


public interface IProductService {

    Product getProductById(Long Id);
}
