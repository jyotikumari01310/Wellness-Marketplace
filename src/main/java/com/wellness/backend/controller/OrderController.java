package com.wellness.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.wellness.backend.entity.Order;
import com.wellness.backend.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Order place(
            @RequestParam String email,
            @RequestParam Long productId) {

        return service.placeOrder(email, productId);
    }

    @GetMapping
    public List<Order> my(@RequestParam String email) {
        return service.myOrders(email);
    }
}
