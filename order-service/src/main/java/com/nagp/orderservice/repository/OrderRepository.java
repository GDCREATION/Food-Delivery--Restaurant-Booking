package com.nagp.orderservice.repository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepository {

    private final Map<Long, Order> orderMap = new HashMap<>();
    private final List<Order> orderList = new ArrayList<>();
    private Long currentId = 1L;

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderMap.get(id));
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(currentId);
            currentId++;
        }
        orderMap.put(order.getId(), order);
        orderList.add(order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderList);
    }

    // Other methods...

}

