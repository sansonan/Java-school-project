package com.example.school.service;

import com.example.school.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategoryById(Long categoryId);
    List<Category> getAllCategories();
    Category updateCategory(Long id, Category category);
    String deleteCategory(Long id);
}
