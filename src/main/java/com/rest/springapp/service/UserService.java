package com.rest.springapp.service;

import com.rest.springapp.model.User;
import com.rest.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User addUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public List<User> getUsersSortedByName() {
        return repository.findAllByOrderByNameAsc();
    }

    public Page<User> getUsersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public List<User> getUsersByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }

    public void deleteUser(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    public Object updateUser(int id, User user) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }
}
