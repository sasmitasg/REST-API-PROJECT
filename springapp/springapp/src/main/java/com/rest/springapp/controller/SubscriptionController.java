package com.rest.springapp.controller;

import com.rest.springapp.model.Subscription;
import com.rest.springapp.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription) {
        return new ResponseEntity<>(service.addSubscription(subscription), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return new ResponseEntity<>(service.getAllSubscriptions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable int id) {
        Optional<Subscription> subscription = service.getSubscriptionById(id);
        return subscription.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                           .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable int id) {
        try {
            service.deleteSubscription(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Subscription>> getSubscriptionsWithPagination(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getSubscriptionsWithPagination(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable int id, @RequestBody Subscription subscription) {
        return ResponseEntity.ok(service.updateSubscription(id, subscription));
    }
}
