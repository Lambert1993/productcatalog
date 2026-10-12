package com.ecommerce.productcatalogservice.controller;

import com.ecommerce.productcatalogservice.dto.CategoryDto;
import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.dto.ProductPitchDto;
import com.ecommerce.productcatalogservice.model.Product;
import com.ecommerce.productcatalogservice.service.DummyProductService;
import com.ecommerce.productcatalogservice.service.IProductPitchService;
import com.ecommerce.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;
    private IProductPitchService productPitchService;
    public ProductController(IProductService productService, IProductPitchService productPitchService) {
        this.productService=productService;
        this.productPitchService=productPitchService;
    }

    @GetMapping
    List<Product> getAllProducts(){
        return List.of();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){

        Product product = productService.getProductById(id);

        if(product==null){
            return new ResponseEntity<>((HttpHeaders) null,HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
        }

        ProductDto dto = from(product);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}/pitch")
    public ResponseEntity<ProductPitchDto> generatePitch(@PathVariable(name="id") Long id){

        if(id<=0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productService.getProductById(id);
        ProductPitchDto pitchDto = new ProductPitchDto();
        pitchDto.setId(product.getId());
        String pitch = productPitchService.generatePitchForProductId(product);
        pitchDto.setPitch(pitch);

        return new ResponseEntity<>(pitchDto,HttpStatus.OK);
    }

    private ProductDto from (Product product){
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice().doubleValue());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDescription(product.getCategory().getDescription());
        categoryDto.setName(product.getCategory().getName());
        categoryDto.setId(product.getCategory().getId());
        dto.setCategory(categoryDto);
        return dto;
    }
}
