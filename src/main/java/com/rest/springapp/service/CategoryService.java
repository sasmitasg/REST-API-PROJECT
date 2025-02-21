package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Category;
import com.rest.springapp.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category addCategory(Category category) {
        return repo.save(category);
    }

    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    public Category getCategoryById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Page<Category> getCategoriesWithPaginationAndSorting(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Page<Category> searchCategories(String name, Pageable pageable) {
        return repo.searchByName(name, pageable);
    }

    public Category updateCategory(int id, Category updatedCategory) {
        return repo.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    return repo.save(existingCategory);
                })
                .orElse(null);
    }

    public void deleteCategory(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }
}
