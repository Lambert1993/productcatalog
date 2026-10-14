package com.ecommerce.productcatalogservice.controller;

import com.ecommerce.productcatalogservice.dto.CategoryDto;
import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.dto.ProductPitchDto;
import com.ecommerce.productcatalogservice.model.Product;
import com.ecommerce.productcatalogservice.service.DummyProductService;
import com.ecommerce.productcatalogservice.service.IProductPitchService;
import com.ecommerce.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService storageService;
    private final IProductPitchService productPitchService;
    private final IProductService supplierService;
    public ProductController(IProductService storageService, IProductPitchService productPitchService,
                             @Qualifier("dummyProductService") IProductService supplierService) {
        this.storageService=storageService;
        this.productPitchService=productPitchService;
        this.supplierService = supplierService;
    }

    @GetMapping
    List<Product> getAllProducts(){
        return List.of();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){

        Product product = storageService.getProductById(id);

        ProductDto dto;

        if(product!=null){
            dto = from(product);
        }
        else{
            return new ResponseEntity<String>("Product Not Found for Id: "+id,HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
        }

        return new ResponseEntity<ProductDto>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}/pitch")
    public ResponseEntity<ProductPitchDto> generatePitch(@PathVariable(name="id") Long id){

        if(id<=0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = storageService.getProductById(id);
        ProductPitchDto pitchDto = new ProductPitchDto();
        pitchDto.setId(product.getId());
        String pitch = productPitchService.generatePitchForProductId(product);
        pitchDto.setPitch(pitch);

        return new ResponseEntity<>(pitchDto,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody  ProductDto productDto){
        if(Objects.isNull(productDto)){
            return new ResponseEntity<String>("Not Created input is null",HttpStatus.BAD_REQUEST);
        }
        Product respopnseDto = storageService.createProduct(productDto);
        return new ResponseEntity<ProductDto>(from(respopnseDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody  ProductDto productDto){

      Product product =  storageService.updateProduct(productDto);

      if(null==product){
          return new ResponseEntity<>("Unable to Update the product as it is not found or active",HttpStatus.BAD_REQUEST);
      }

        return new ResponseEntity<ProductDto>(from(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){

       Boolean isdeleted = storageService.deleteProductById(id);

       if(!isdeleted)
           return new ResponseEntity<>("Unable to delete the product of this id: "+id,HttpStatus.NOT_FOUND);

       return new ResponseEntity<>("Successfully deleted the product of id: "+id,HttpStatus.OK);
    }

    private ProductDto from (Product product){
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setId(product.getId());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDescription(product.getCategory().getDescription());
        categoryDto.setName(product.getCategory().getName());
        categoryDto.setId(product.getCategory().getId());
        dto.setCategory(categoryDto);
        return dto;
    }
}
