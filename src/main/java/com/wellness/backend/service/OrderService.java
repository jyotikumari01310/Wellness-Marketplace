package com.wellness.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wellness.backend.entity.Order;
import com.wellness.backend.entity.Product;
import com.wellness.backend.entity.User;
import com.wellness.backend.repository.OrderRepository;
import com.wellness.backend.repository.ProductRepository;
import com.wellness.backend.repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo,
                        UserRepository userRepo,
                        ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public Order placeOrder(String email, Long productId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderTime(LocalDateTime.now());

        return orderRepo.save(order);
    }

    public List<Order> myOrders(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepo.findByUser(user);
    }
}
