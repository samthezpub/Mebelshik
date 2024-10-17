package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.SuperCategory;
import com.example.mebelshik.Service.SuperCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/supercategory")
public class SuperCategoryController {
    private final SuperCategoryService superCategoryService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(superCategoryService.findAllSuperCategories(), HttpStatus.OK);

    }

    @GetMapping("/get/id")
    public ResponseEntity<?> getCategoriesBySupercategoryId(Long id){
        return new ResponseEntity<>(superCategoryService.findAllCategoriesBySuperCategoryId(id), HttpStatus.OK);
    }
}
