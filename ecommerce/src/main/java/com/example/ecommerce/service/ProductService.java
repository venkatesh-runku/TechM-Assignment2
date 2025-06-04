package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> filterByCategory(String category);
    List<Product> filterByPriceRange(double min, double max);
    List<Product> searchByName(String name);
}
