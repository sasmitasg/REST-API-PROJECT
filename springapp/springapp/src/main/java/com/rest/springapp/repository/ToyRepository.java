package com.rest.springapp.repository;

import com.rest.springapp.model.Toy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToyRepository extends JpaRepository<Toy, Integer> {
    List<Toy> findByCategory_Name(String categoryName);

    Page<Toy> findByCategory_Name(String categoryName, Pageable pageable);

    Toy findByName(String name);

    @Query("SELECT t FROM Toy t WHERE t.price BETWEEN :minPrice AND :maxPrice")
    Page<Toy> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice, Pageable pageable);

    @Query("SELECT t FROM Toy t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Toy> searchByName(@Param("keyword") String keyword, Pageable pageable);

    long countByCategory_Name(String categoryName);
}
