package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Order;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private final ConcurrentMap<String, Order> orders = new ConcurrentHashMap<>();

    public Order save(Order order) {
        orders.put(order.id(), order);
        return order;
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }
}
