package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.model.Product;


public interface IProductService {

    Product getProductById(Long Id);

    Product createProduct(ProductDto productDto);

    Product updateProduct(ProductDto productDto);

    Boolean deleteProductById(Long id);
}
