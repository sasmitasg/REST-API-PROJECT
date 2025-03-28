package com.rest.springapp.repository;

import com.rest.springapp.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
    @Query("SELECT o FROM Order o WHERE o.totalAmount > ?1")
    Page<Order> findOrdersByAmountGreaterThan(double amount, Pageable pageable);
    
    @Query("SELECT o FROM Order o WHERE o.user.id = ?1")
    Page<Order> findOrdersByUserId(int userId, Pageable pageable);
    
    @Query("SELECT o FROM Order o WHERE o.totalAmount < ?1")
    Page<Order> findOrdersByAmountLessThan(double amount, Pageable pageable);
}
