package com.rest.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Category;
import com.rest.springapp.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(service.addCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = service.getCategoryById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        Category category = service.getCategoryByName(name);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<Category>> getCategoriesWithPaginationAndSorting(Pageable pageable) {
        return ResponseEntity.ok(service.getCategoriesWithPaginationAndSorting(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Category>> searchCategories(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(service.searchCategories(name, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category updatedCategory = service.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @PutMapping("/name/{name}")
    public ResponseEntity<Category> updateCategoryByName(@PathVariable String name, @RequestBody Category category) {
        Category updatedCategory = service.updateCategoryByName(name, category);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteCategoryByName(@PathVariable String name) {
        service.deleteCategoryByName(name);
        return ResponseEntity.noContent().build();
    }
}
