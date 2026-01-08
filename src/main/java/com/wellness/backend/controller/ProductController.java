package com.wellness.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Product;
import com.wellness.backend.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping
    public List<Product> all() {
        return service.getAllProducts();
    }
}
