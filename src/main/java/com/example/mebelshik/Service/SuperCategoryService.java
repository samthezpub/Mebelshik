package com.example.mebelshik.Service;


import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Entitiy.SuperCategory;

import java.util.List;

public interface SuperCategoryService {
    List<SuperCategory> findAllSuperCategories();
    List<Category> findAllCategoriesBySuperCategoryId(Long id);
}
