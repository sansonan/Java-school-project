package com.example.school.controller;

import com.example.school.dto.CategoryDTO;
import com.example.school.entity.Category;
import com.example.school.service.CategoryService;
import com.example.school.service.util.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Endpoint to create a new category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDTO);
        category = categoryService.createCategory(category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDTO(category));
    }

    // Endpoint to get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> list = categoryService.getAllCategories()
                .stream()
                .map(category ->CategoryMapper.INSTANCE.toCategoryDTO(category) )
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // Endpoint to update a category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryUpdate) {
        Category category = CategoryMapper.INSTANCE.toCategory(categoryUpdate);
        Category update = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDTO(update));
    }

    // Endpoint to delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        String deleteCategory = categoryService.deleteCategory(id);
        return ResponseEntity.ok(deleteCategory);
    }
}
