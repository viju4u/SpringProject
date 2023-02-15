package com.ShoppingApp.ProductService.repository;

import com.ShoppingApp.ProductService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,Long> {
}
