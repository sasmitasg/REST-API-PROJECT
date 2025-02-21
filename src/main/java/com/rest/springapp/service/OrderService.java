package com.rest.springapp.service;

import com.rest.springapp.model.Order;
import com.rest.springapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return repository.findById(id);
    }

    public void deleteOrder(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public Page<Order> getOrdersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Page<Order> getOrdersByAmountGreaterThan(double amount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findOrdersByAmountGreaterThan(amount, pageable);
    }

    public Page<Order> getOrdersByUserId(int userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findOrdersByUserId(userId, pageable);
    }

    public Page<Order> getOrdersByAmountLessThan(double amount, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findOrdersByAmountLessThan(amount, pageable);
    }

    public List<Order> getOrdersSortedByTotalAmount(String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), "totalAmount");
        return repository.findAll(sort);
    }

    public Order updateOrder(int id, Order updatedOrder) {
        Optional<Order> existingOrder = repository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setTotalAmount(updatedOrder.getTotalAmount());  // Update other fields as needed
            order.setOrderDate(updatedOrder.getOrderDate());  // Example: add more fields if required
            order.setId(updatedOrder.getId());  // Example: update user ID if required
            return repository.save(order);
        }
        return null;  // Order not found
    }
}
