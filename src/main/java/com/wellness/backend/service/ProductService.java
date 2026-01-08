package com.wellness.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.Product;
import com.wellness.backend.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
}
