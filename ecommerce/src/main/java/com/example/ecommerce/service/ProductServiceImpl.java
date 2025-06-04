package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updated) {
        Product product = getProductById(id);
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        product.setCategory(updated.getCategory());
        product.setStockQuantity(updated.getStockQuantity());
        return repo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Product> filterByCategory(String category) {
        return repo.findByCategory(category);
    }

    @Override
    public List<Product> filterByPriceRange(double min, double max) {
        return repo.findByPriceBetween(min, max);
    }

    @Override
    public List<Product> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
}
