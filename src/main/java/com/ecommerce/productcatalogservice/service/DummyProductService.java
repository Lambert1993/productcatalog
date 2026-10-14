package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.DummyJsonProductDto;
import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.model.Category;
import com.ecommerce.productcatalogservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@Slf4j
public class DummyProductService implements IProductService {

    private RestTemplate restTemplate;

    public DummyProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Product getProductById(Long id) {
        Product product;
        try {
            log.info("Start to read data for id: {} from the supplier dummyjson",id);
            ResponseEntity<DummyJsonProductDto> jsonDto = restTemplate
                    .getForEntity("https://dummyjson.com/products/{id}", DummyJsonProductDto.class, id);
            if(jsonDto.getStatusCode().is2xxSuccessful())
            product=from(jsonDto.getBody());
            else
                return null;
        }catch (HttpClientErrorException.NotFound errorException){
            return null;
        }
        return product;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Product updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Boolean deleteProductById(Long id) {
        return false;
    }

    private Product from(DummyJsonProductDto dummyJsonProductDto){
        Product product = new Product();

        product.setId(dummyJsonProductDto.getId());
        product.setName(dummyJsonProductDto.getTitle());
        product.setDescription(dummyJsonProductDto.getDescription());
        product.setPrice(BigDecimal.valueOf(dummyJsonProductDto.getPrice()));

        Category category = new Category();
        category.setName(dummyJsonProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
