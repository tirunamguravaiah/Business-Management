package com.business.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.business.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    // Custom finder method to search by product name
    Product findByPname(String name);
}
