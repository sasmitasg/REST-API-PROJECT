package com.rest.springapp.controller;

import com.rest.springapp.model.Order;
import com.rest.springapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return new ResponseEntity<>(service.addOrder(order), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = service.getOrderById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        try {
            service.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Order>> getOrdersWithPagination(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getOrdersWithPagination(page, size));
    }

    @GetMapping("/paginate/amount/greaterThan")
    public ResponseEntity<Page<Order>> getOrdersByAmountGreaterThan(@RequestParam double amount, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getOrdersByAmountGreaterThan(amount, page, size));
    }

    @GetMapping("/paginate/user/{userId}")
    public ResponseEntity<Page<Order>> getOrdersByUserId(@PathVariable int userId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getOrdersByUserId(userId, page, size));
    }

    @GetMapping("/paginate/amount/lessThan")
    public ResponseEntity<Page<Order>> getOrdersByAmountLessThan(@RequestParam double amount, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getOrdersByAmountLessThan(amount, page, size));
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Order>> getOrdersSortedByAmount(@RequestParam String direction) {
        return ResponseEntity.ok(service.getOrdersSortedByTotalAmount(direction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order order) {
        return ResponseEntity.ok(service.updateOrder(id, order));
    }
}
