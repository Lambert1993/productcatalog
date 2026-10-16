package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.CategoryDto;
import com.ecommerce.productcatalogservice.dto.DummyJsonProductDto;
import com.ecommerce.productcatalogservice.dto.ImportDummyJsonProductDto;
import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service("importService")
@Slf4j
public class ImportProductService implements  IProductService{

    private final RestTemplate restTemplate;
    private final StorageProductService storageProductService;

    public ImportProductService(RestTemplateBuilder restTemplateBuilder, StorageProductService storageProductService) {
        this.restTemplate = restTemplateBuilder.build();
        this.storageProductService = storageProductService;
    }


    @Override
    public String importAllDummyProducts() {
        int count =0 ;
        try {
            log.info("Start to read data for from the supplier dummyjson");
            ResponseEntity<ImportDummyJsonProductDto> jsonDto = restTemplate
                    .getForEntity("https://dummyjson.com/products?limit=0", ImportDummyJsonProductDto.class);
            if(jsonDto.getStatusCode().is2xxSuccessful()){
                count=jsonDto.getBody().getProducts().size();
                jsonDto.getBody().getProducts().forEach(x->storageProductService.createProduct(from(x)));
            }
            else
                return null;
        }catch (HttpClientErrorException.NotFound errorException){
            return null;
        }
        return "Successfully imported:::"+count+" records";
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Page<Product> getProductsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return null;
    }

    @Override
    public Product updateProductInfo(ProductDto dto) {
        return null;
    }

    private ProductDto from(DummyJsonProductDto dummyJsonProductDto){
        ProductDto product = new ProductDto();

        product.setName(dummyJsonProductDto.getTitle());
        product.setDescription(dummyJsonProductDto.getDescription());
        product.setPrice(BigDecimal.valueOf(dummyJsonProductDto.getPrice()));

        CategoryDto category = new CategoryDto();
        category.setName(dummyJsonProductDto.getCategory());
        product.setCategory(category);

        return product;
    }


    @Override
    public Product getProductById(Long Id) {
        return null;
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
        return null;
    }


}
