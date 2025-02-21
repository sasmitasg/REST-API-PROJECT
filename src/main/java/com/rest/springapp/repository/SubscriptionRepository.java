package com.rest.springapp.repository;

import com.rest.springapp.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Page<Subscription> findByPlan(String plan, Pageable pageable);
}
