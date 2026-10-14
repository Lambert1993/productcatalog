package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.CategoryDto;
import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.model.Category;
import com.ecommerce.productcatalogservice.model.Product;
import com.ecommerce.productcatalogservice.model.enums.State;
import com.ecommerce.productcatalogservice.repository.CategoryRepository;
import com.ecommerce.productcatalogservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Primary
@Service
@Slf4j
public class StorageProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public StorageProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long Id) {
        log.info("Start to read data for id: {}",Id);
        Optional<Product> productOptional = productRepository.findByIdAndState(Id,State.ACTIVE);

        log.info("retrieved data is: {}",productOptional.isPresent());
        return productOptional.orElse(null);
    }

    @Override
    public Product createProduct(ProductDto productDto) {

        Product product = from((Product) null, productDto);

        Category category = findCategoryByName(productDto.getCategory().getName());

        if(null==category){
            category = from(null, productDto.getCategory());
            category.setCreatedAt(new Date());
            category.setLastUpdatedAt(new Date());
            category.setState(State.ACTIVE);
        }
        product.setCategory(category);
        product.setCreatedAt(new Date());
        product.setLastUpdatedAt(new Date());
        product.setState(State.ACTIVE);

    return  productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto productDto) {

       Product product = productRepository.findByIdAndState(productDto.getId(),State.ACTIVE).orElse(null);
       if(null==product)
           return null;

        product = from(product,productDto);
        product.setLastUpdatedAt(new Date());

        return  productRepository.save(product);
    }

    @Override
    public Boolean deleteProductById(Long id) {

        Product product = productRepository.findByIdAndState(id, State.ACTIVE).orElse(null);

        if(null==product)
            return false;

        product.setState(State.INACTIVE);
        productRepository.save(product);
        return true;
    }

    private Category findCategoryByName(String name){
       return categoryRepository.findByName(name).orElse(null);
    }

    private Product from(Product product, ProductDto dto){

        if(product==null)
         product = new Product();

        product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setName(dto.getName());
            return product;
    }

    private Category from(Category category, CategoryDto dto){
        if(null==category)
         category = new Category();

        if(dto==null)
            return category;

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setId(dto.getId());

        return category;
    }


}
