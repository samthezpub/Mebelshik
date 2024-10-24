package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;
import com.example.mebelshik.Service.Impl.CatalogProductServiceImpl;
import com.example.mebelshik.Service.Impl.ProductFilterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/catalog")
@RestController
public class CatalogProductController {
    private final CatalogProductServiceImpl catalogProductService;
    private final ProductFilterServiceImpl productFilterService;

    // TODO create

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCatalogProduct(@PathVariable Long id){
        try {
            CatalogProduct catalogProduct = catalogProductService.findCatalogProduct(id);

            return new ResponseEntity<>(catalogProduct, HttpStatus.OK);
        } catch (CatalogProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{category_id}/get-all")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable Long category_id){
        List<CatalogProduct> catalogProducts = catalogProductService.findCatalogProductsByCategoryId(category_id);

        return new ResponseEntity<>(catalogProducts, HttpStatus.OK);
    }

    @GetMapping("/get/{slug}")
    public ResponseEntity<?> getCatalogProductBySlug(@PathVariable String slug){
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
