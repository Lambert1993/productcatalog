package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;


public interface IProductService {

    Product getProductById(Long Id);

    Product createProduct(ProductDto productDto);

    Product updateProduct(ProductDto productDto);

    Boolean deleteProductById(Long id);

    String importAllDummyProducts();

    List<Product> getAllProducts();

    Page<Product> getProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Product updateProductInfo(ProductDto dto);
}
