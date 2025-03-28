package com.rest.springapp.service;

import com.rest.springapp.model.User;
import com.rest.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User addUser(User user) {
        return repository.save(user);
    }

    public Page<User> getUsersWithPagination(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByName(String name) {
        return repository.findByName(name);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Transactional
    public User updateUser(int id, User user) {
        Optional<User> existingUser = repository.findById(id);
        if (existingUser.isPresent()) {
            existingUser.get().setName(user.getName());
            existingUser.get().setEmail(user.getEmail());
            return repository.save(existingUser.get());
        }
        throw new RuntimeException("User not found!");
    }

    public User updateUserByName(String name, User user) {
        User existingUser = repository.findByName(name);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return repository.save(existingUser);
        }
        return null;
    }

    public User updateUserByEmail(String email, User user) {
        User existingUser = repository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return repository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    public void deleteUserByName(String name) {
        User existingUser = repository.findByName(name);
        if (existingUser != null) {
            repository.delete(existingUser);
        }
    }

    public void deleteUserByEmail(String email) {
        User existingUser = repository.findByEmail(email);
        if (existingUser != null) {
            repository.delete(existingUser);
        }
    }
}
