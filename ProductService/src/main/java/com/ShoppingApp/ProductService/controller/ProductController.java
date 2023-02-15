package com.ShoppingApp.ProductService.controller;

import com.ShoppingApp.ProductService.Dto.ProductRequest;
import com.ShoppingApp.ProductService.Dto.ProductResponse;
import com.ShoppingApp.ProductService.model.Product;
import com.ShoppingApp.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }
}
