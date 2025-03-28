package com.rest.springapp.controller;

import com.rest.springapp.model.Toy;
import com.rest.springapp.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/name/{name}")
    public ResponseEntity<Toy> getToyByName(@PathVariable String name) {
        Toy toy = service.getToyByName(name);
        return toy != null ? new ResponseEntity<>(toy, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Toy>> getToysByCategory(@PathVariable String categoryName) {
        List<Toy> toys = service.getToysByCategory(categoryName);
        return toys.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(toys, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Toy> updateToy(@PathVariable int id, @RequestBody Toy newToy) {
        Toy updatedToy = service.updateToy(id, newToy);
        return new ResponseEntity<>(updatedToy, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToy(@PathVariable int id) {
        service.deleteToy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Toy>> getToysWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(service.getToysWithPagination(page, size, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}/paginated")
    public ResponseEntity<Page<Toy>> getToysByCategoryWithPagination(
            @PathVariable String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(service.getToysByCategoryWithPagination(categoryName, page, size, sortBy, sortDir), HttpStatus.OK);
    }
}
