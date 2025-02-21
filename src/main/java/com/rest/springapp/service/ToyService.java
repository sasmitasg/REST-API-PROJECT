package com.rest.springapp.service;

import com.rest.springapp.model.Toy;
import com.rest.springapp.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToyService {

    @Autowired
    private ToyRepository repository;

    public Toy addToy(Toy toy) {
        return repository.save(toy);
    }

    public List<Toy> getAllToys() {
        return repository.findAll();
    }

    public Optional<Toy> getToyById(int id) {
        return repository.findById(id);
    }

    public Toy updateToy(int id, Toy newToy) {
        return repository.findById(id)
                .map(toy -> {
                    toy.setName(newToy.getName());
                    toy.setPrice(newToy.getPrice());
                    toy.setCategory(newToy.getCategory());
                    return repository.save(toy);
                })
                .orElseThrow(() -> new RuntimeException("Toy not found"));
    }

    public void deleteToy(int id) {
        repository.deleteById(id);
    }

    public Page<Toy> getToysWithPagination(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    public Page<Toy> getToysByCategory(String categoryName, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findByCategory(categoryName, pageable);
    }
}
