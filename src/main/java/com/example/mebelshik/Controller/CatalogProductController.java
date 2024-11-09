package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Entitiy.Category;
import com.example.mebelshik.Entitiy.Filter;
import com.example.mebelshik.Entitiy.ProductFilter;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;
import com.example.mebelshik.Exception.CategoryNotFoundException;
import com.example.mebelshik.Exception.FilterNotFoundException;
import com.example.mebelshik.Request.UpdateProductRequest;
import com.example.mebelshik.Service.Impl.CatalogProductServiceImpl;
import com.example.mebelshik.Service.Impl.CategoryServiceImpl;
import com.example.mebelshik.Service.Impl.FileUploadServiceImpl;
import com.example.mebelshik.Service.Impl.ProductFilterServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/catalog")
@RestController
public class CatalogProductController {
    private final CatalogProductServiceImpl catalogProductService;
    private final ProductFilterServiceImpl productFilterService;
    private final CategoryServiceImpl categoryService;
    private final FileUploadServiceImpl fileUploadService;

    @PostMapping("/create")
    public ResponseEntity<CatalogProduct> createCatalogProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Float price,
            @RequestParam("title") String title,
            @RequestParam("category") Long categoryId,
            @RequestParam Map<Long, String> filtersIds, // для фильтров
            @RequestPart("file") List<MultipartFile> files // для списка файлов
    ) {
        // Создаем CatalogProduct и обрабатываем файлы
        CatalogProduct catalogProduct = new CatalogProduct();
        catalogProduct.setName(name);
        catalogProduct.setDescription(description);
        catalogProduct.setPrice(price);
        catalogProduct.setTitleSEO(name + " купить в Мариуполе за " + price +  "рублей в интернет магазине ArtMebel");
        catalogProduct.setDescriptionSEO(name + " в городе Мариуполь за " + price +"рублей. В интенет-магазине ArtMebel. Бесплатная доставка по Мариуполю.");

        catalogProduct.setKeywords(catalogProductService.generateKeywords(name));

        String slug = catalogProductService.generateSlug(name);

        catalogProduct.setSlug(slug);


        int photosCount = 0;

        for (Integer i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);

            try {
                fileUploadService.saveFileForProductByProductSlugAndIterator(file, slug, photosCount + 1);

                photosCount++;
            } catch (IOException e) {
                continue;
            }
        }

        catalogProduct.setPhotosCount((short) photosCount);

        try {
            catalogProduct.setCategory(categoryService.findCategory(categoryId));
        } catch (CategoryNotFoundException e) {
            catalogProduct.setCategory(null);
        }

        catalogProductService.createCatalogProduct(catalogProduct);

        List<ProductFilter> productFilters = new ArrayList<>();

        for (var filter : filtersIds.entrySet()) {
            System.out.println(filter.getKey());
            System.out.println(filter.getValue());
        }
        ;

//        filtersIds.stream().forEach(filterId -> {
//            productFilters.add(productFilterService.createProductFilter(catalogProduct.getId(), filterId, ))
//        });
//
//        catalogProduct.setProductFilters(filters); // если filters — это Map или измените под ваш тип


        return new ResponseEntity<>(catalogProduct, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCatalogProduct(
            @PathVariable("id") Long productId,
            @ModelAttribute UpdateProductRequest request, // Изменение здесь
            @RequestPart(value = "file", required = false) List<MultipartFile> files, // Отдельно для файлов
            @RequestParam(required = false) String filters) {

        try {
            CatalogProduct product = catalogProductService.findCatalogProduct(productId);

            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setTitleSEO(request.getName() + " купить в Мариуполе за " + Integer.parseInt(request.getPrice().toString()) +  " рублей в интернет магазине ArtMebel");
            product.setDescriptionSEO(request.getName() + " в городе Мариуполь за " + request.getPrice() +"рублей. В интенет-магазине ArtMebel. Бесплатная доставка по Мариуполю.");

            product.setKeywords(catalogProductService.generateKeywords(request.getName()));


            Category category = categoryService.findCategory(request.getCategoryId());

            product.setCategory(category);

            String slug = catalogProductService.generateSlug(request.getName());
            product.setSlug(slug);

            int photosCount = 0;

            if (files != null) {
                for (Integer i = 0; i < files.size(); i++) {
                    MultipartFile file = files.get(i);

                    try {
                        fileUploadService.saveFileForProductByProductSlugAndIterator(file, slug, photosCount + 1);

                        photosCount++;
                    } catch (IOException e) {
                        continue;
                    }
                }
            }

            product.setPhotosCount((short) photosCount);

            Map<String, String> filtersMapped = new HashMap<>();

            if (filters != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    filtersMapped = objectMapper.readValue(filters, new TypeReference<Map<String, String>>() {
                    });
                } catch (IOException e) {
                    // Обработка исключения
                    return new ResponseEntity<>("Не указаны фильтры", HttpStatus.BAD_REQUEST);
                }
            }

            List<ProductFilter> filterList = new ArrayList<>();

            filtersMapped.forEach((key, value) -> {
                try {
                    Long filterId = Long.parseLong(key);
                    System.out.println(filterId);
                    ProductFilter productFilter = productFilterService.getFilterByProductIdAndFilterId(productId,filterId);
                    productFilter.setValue(value);

                    ProductFilter filter = productFilterService.updateProductFilter(productFilter);
                    filterList.add(filter);
                } catch (FilterNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            product.setProductFilters(filterList);

            catalogProductService.updateCatalogProduct(product);

            return new ResponseEntity<>("Успешно сохранёно!", HttpStatus.OK);

        } catch (CatalogProductNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(catalogProductService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCatalogProduct(@PathVariable Long id) {
        try {
            CatalogProduct catalogProduct = catalogProductService.findCatalogProduct(id);

            return new ResponseEntity<>(catalogProduct, HttpStatus.OK);
        } catch (CatalogProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCatalogProduct(@PathVariable Long id) {
        catalogProductService.deleteCatalogProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{category_id}/get-all")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable Long category_id) {
        List<CatalogProduct> catalogProducts = catalogProductService.findCatalogProductsByCategoryId(category_id);

        return new ResponseEntity<>(catalogProducts, HttpStatus.OK);
    }

    @GetMapping("/get/{slug}")
    public ResponseEntity<?> getCatalogProductBySlug(@PathVariable String slug) {
        try {
            return new ResponseEntity<>(catalogProductService.findCatalogProductBySlug(slug), HttpStatus.OK);
        } catch (CatalogProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/get/filter")
    public ResponseEntity<List<CatalogProduct>> getProductsByFilters(
            @RequestParam List<Long> filterIds,
            @RequestParam List<String> values) {

        if (filterIds.size() != values.size()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long filterCount = (long) filterIds.size();
        List<CatalogProduct> products = catalogProductService.findCatalogProductsByFilters(filterIds, values, filterCount);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CatalogProduct>> searchProducts(@RequestParam String query) {
        List<CatalogProduct> products = catalogProductService.searchByName(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
