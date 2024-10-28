package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Entitiy.Filter;
import com.example.mebelshik.Exception.CategoryNotFoundException;
import com.example.mebelshik.Exception.FilterNotFoundException;
import com.example.mebelshik.Request.FilterCreateRequest;
import com.example.mebelshik.Service.Impl.CategoryServiceImpl;
import com.example.mebelshik.Service.Impl.FilterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filters")
@RequiredArgsConstructor
public class FilterController {

    private final FilterServiceImpl filterService;
    private final CategoryServiceImpl categoryService;

    // Получить все фильтры
    @GetMapping
    public ResponseEntity<List<Filter>> getAllFilters() {
        List<Filter> filters = filterService.getAllFilters();
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Filter> getFilterById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(filterService.getFilterById(id), HttpStatus.OK);
        } catch (FilterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Добавить новый фильтр
    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Filter> addFilter(@RequestBody FilterCreateRequest filter) {

        Filter savedFilter = new Filter();

        savedFilter.setFilterType(filter.getFilterType());
        savedFilter.setValues(filter.getValues());

        filterService.saveFilter(savedFilter);


        try {
            Category category = categoryService.findCategory(filter.getCategory_id());

            List<Filter> categoryFilters = category.getFilters();
            categoryFilters.add(savedFilter);
            category.setFilters(categoryFilters);
            categoryService.updateCategory(category);
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(savedFilter, HttpStatus.CREATED);
    }

    // Редактировать фильтр
    @PostMapping("/update/{id}")
    public ResponseEntity<Filter> updateFilter(@PathVariable Long id, @RequestBody Filter filterDetails) {
        Filter updatedFilter = filterService.updateFilter(id, filterDetails);
        return new ResponseEntity<>(updatedFilter, HttpStatus.OK);
    }

    // Удалить фильтр
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFilter(@PathVariable Long id) {
        filterService.deleteFilter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
