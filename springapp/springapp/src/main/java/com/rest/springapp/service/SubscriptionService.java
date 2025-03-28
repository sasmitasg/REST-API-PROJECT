package com.rest.springapp.service;

import com.rest.springapp.model.Subscription;
import com.rest.springapp.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    public Subscription addSubscription(Subscription subscription) {
        return repository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return repository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(int id) {
        return repository.findById(id);
    }

    public void deleteSubscription(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Subscription not found");
        }
    }

    public Page<Subscription> getSubscriptionsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  
        return repository.findAll(pageable);  
    }

    public Subscription updateSubscription(int id, Subscription updatedSubscription) {
        Optional<Subscription> existingSubscription = repository.findById(id);
        if (existingSubscription.isPresent()) {
            Subscription subscription = existingSubscription.get();
            subscription.setStartDate(updatedSubscription.getStartDate());  
            subscription.setEndDate(updatedSubscription.getEndDate());  
            subscription.setStartDate(updatedSubscription.getStartDate());  
            subscription.setUser(updatedSubscription.getUser()); 
            return repository.save(subscription);
        }
        return null; 
    }
}
