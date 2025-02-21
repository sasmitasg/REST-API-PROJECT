package com.rest.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import com.rest.springapp.model.Toy;
import com.rest.springapp.service.ToyService;

@RestController
@RequestMapping("/api/toys")
public class ToyController {

    @Autowired
    private ToyService service;

    @PostMapping
    public ResponseEntity<Toy> addToy(@RequestBody Toy toy) {
        return new ResponseEntity<>(service.addToy(toy), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Toy>> getAllToys() {
        return new ResponseEntity<>(service.getAllToys(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Toy> getToyById(@PathVariable int id) {
        return service.getToyById(id)
                .map(toy -> new ResponseEntity<>(toy, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Toy> updateToy(@PathVariable int id, @RequestBody Toy newToy) {
        return new ResponseEntity<>(service.updateToy(id, newToy), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToy(@PathVariable int id) {
        service.deleteToy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Pagination and Sorting
    @GetMapping("/paginate")
    public ResponseEntity<Page<Toy>> getToysWithPagination(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Page<Toy> toysPage = service.getToysWithPagination(page, size, sortBy, sortDir);
        return new ResponseEntity<>(toysPage, HttpStatus.OK);
    }

    // Fetch toys by category using JPQL with pagination & sorting
    @GetMapping("/category")
    public ResponseEntity<Page<Toy>> getToysByCategory(
            @RequestParam String categoryName,
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<Toy> toysPage = service.getToysByCategory(categoryName, page, size, sortBy, sortDir);
        return new ResponseEntity<>(toysPage, HttpStatus.OK);
    }
}
