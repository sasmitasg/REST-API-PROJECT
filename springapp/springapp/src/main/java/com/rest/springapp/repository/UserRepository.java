package com.rest.springapp.repository;

import com.rest.springapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u ORDER BY u.name ASC")
    List<User> findAllByOrderByNameAsc();

    @Query("SELECT u FROM User u ORDER BY u.name DESC")
    List<User> findAllByOrderByNameDesc();

    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByNameContaining(String name);

    @Query("SELECT u FROM User u")
    Page<User> getUsersWithPagination(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.email = :email WHERE u.id = :id")
    void updateUserById(int id, String name, String email);

    User findByName(String name);  

    User findByEmail(String email);  
}
