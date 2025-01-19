package com.antoine.springJwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.antoine.springJwt.model.Category;
import com.antoine.springJwt.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
