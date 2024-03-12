package com.nagp.orderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final RestaurantInfoServiceClient restaurantInfoServiceClient;

    public OrderController(OrderService orderService, RestaurantInfoServiceClient restaurantInfoServiceClient) {
        this.orderService = orderService;
        this.restaurantInfoServiceClient = restaurantInfoServiceClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(201).body(createdOrder);
    }

    @GetMapping("/{id}/restaurant")
    public ResponseEntity<RestaurantInfo> getRestaurantInfoForOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        RestaurantInfo restaurantInfo = restaurantInfoServiceClient.getRestaurantInfo(order.getRestaurantId());
        return ResponseEntity.ok(restaurantInfo);
    }

    // Other APIs...

}

