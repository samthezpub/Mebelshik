package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    void createCategory(Category category);
    Category findCategory(Long id) throws CategoryNotFoundException;
    List<Category> findAllCategories();
    void updateCategory(Category category);
    void deleteCategory(Long id);
}
