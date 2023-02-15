package com.ShoppingApp.ProductService.service;

import com.ShoppingApp.ProductService.Dto.ProductRequest;
import com.ShoppingApp.ProductService.Dto.ProductResponse;
import com.ShoppingApp.ProductService.model.Product;
import com.ShoppingApp.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<ProductResponse> getProducts() {
         return productRepository.findAll().stream().map(this::mappedToResponse).toList();
    }

    private ProductResponse mappedToResponse(Product product) {
        ProductResponse productResponse=new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setId(product.getId());
        return productResponse;
    }
    public void createProduct(ProductRequest productRequest) {
        Product product=new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
    }

    /*For adding logs we can use slf4j */

}
