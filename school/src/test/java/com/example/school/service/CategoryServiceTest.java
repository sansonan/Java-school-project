package com.example.school.service;

import com.example.school.entity.Category;
import com.example.school.entity.Permission;
import com.example.school.repository.CategoryRepository;
import com.example.school.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testCreateCategory() {
        //Given
        Category category = new Category();
        category.setName("Test Category");

        //when
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        verify(categoryRepository, times(1)).save(any(Category.class));

        //Then
         assertEquals("Test Category", createdCategory.getName());
    }

    @Test
    public void testGetCategoryById() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Category retrievedCategory = categoryService.getCategoryById(categoryId);

        verify(categoryRepository, times(1)).findById(categoryId);

         assertEquals(categoryId, retrievedCategory.getId());
         assertEquals("Test Category", retrievedCategory.getName());
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        category1.setName("Category 1");

        Category category2 = new Category();
        category2.setName("Category 2");

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> retrievedCategories = categoryService.getAllCategories();

        verify(categoryRepository, times(1)).findAll();

         assertEquals(2, retrievedCategories.size());
         assertEquals("Category 1", retrievedCategories.get(0).getName());
         assertEquals("Category 2", retrievedCategories.get(1).getName());
    }

    @Test
    void testDeletePermission(){
        Category category = new Category();
        category.setId(1L);
        category.setName("BackEnd");

        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).delete(category);

        // Act
        categoryService.deleteCategory(category.getId());

        // Assert
        verify(categoryRepository, times(1)).delete(category);
    }

}
