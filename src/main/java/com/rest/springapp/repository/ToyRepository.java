package com.rest.springapp.repository;

import com.rest.springapp.model.Toy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToyRepository extends JpaRepository<Toy, Integer> {
    
    // Fetch toys by category name (JPQL Query)
    @Query("SELECT t FROM Toy t WHERE t.category.name = :categoryName")
    Page<Toy> findByCategory(@Param("categoryName") String categoryName, Pageable pageable);
}
