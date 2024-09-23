package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Exception.CategoryNotFoundException;
import com.example.mebelshik.Repository.CategoryRepository;
import com.example.mebelshik.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findCategory(Long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Категория не найдена!"));
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryBySlug(String slug) throws CategoryNotFoundException {
        return categoryRepository.findCategoryBySlug(slug).orElseThrow(()-> new CategoryNotFoundException("Категория по slug не найдена!"));
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
