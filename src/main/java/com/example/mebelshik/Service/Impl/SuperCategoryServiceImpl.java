package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Entitiy.SuperCategory;
import com.example.mebelshik.Repository.SuperCategoryRepository;
import com.example.mebelshik.Service.SuperCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SuperCategoryServiceImpl implements SuperCategoryService {
    private final SuperCategoryRepository superCategoryRepository;


    @Override
    public List<SuperCategory> findAllSuperCategories() {
        return superCategoryRepository.findAll();
    }

    @Override
    public List<Category> findAllCategoriesBySuperCategoryId(Long id) {
        return superCategoryRepository.findAllCategoriesBySuperCategoryId(id);
    }
}
