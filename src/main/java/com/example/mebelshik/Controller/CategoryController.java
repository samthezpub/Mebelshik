package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Exception.CategoryNotFoundException;
import com.example.mebelshik.Service.Impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        try {
            Category category = categoryService.findCategory(id);

            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCategories(){
        List<Category> allCategories = categoryService.findAllCategories();

        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
