package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category")
    public List<Product> filterByCategory(@RequestParam String category) {
        return service.filterByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> filterByPrice(@RequestParam double min, @RequestParam double max) {
        return service.filterByPriceRange(min, max);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return service.searchByName(name);
    }
}
