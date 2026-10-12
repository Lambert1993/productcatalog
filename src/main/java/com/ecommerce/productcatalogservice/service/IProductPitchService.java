package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.ProductPitchDto;
import com.ecommerce.productcatalogservice.model.Product;

public interface IProductPitchService {

    String generatePitchForProductId(Product product);
}
