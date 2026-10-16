package com.ecommerce.productcatalogservice.controller;

import com.ecommerce.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InternalProductsController {

    private final IProductService productService;


    public InternalProductsController(@Qualifier("importService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/importAllDummyProducts")
    public ResponseEntity<String> productImport(){

       String response = productService.importAllDummyProducts();

       if(response!=null)
           return new ResponseEntity<>(response, HttpStatus.OK);

       return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
