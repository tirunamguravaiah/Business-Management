package com.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.Product;
import com.business.repositories.ProductRepository;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    // Add Product
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get single product by ID
    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

    // Update product
    public void updateProduct(Product product, String id) {
        if (productRepository.existsById(id)) {
            product.setPid(id); // ensure MongoDB ID consistency
            productRepository.save(product);
        }
    }

    // Delete product
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    // Get product by name
    public Product getProductByName(String name) {
        return productRepository.findByPname(name);
    }
}
