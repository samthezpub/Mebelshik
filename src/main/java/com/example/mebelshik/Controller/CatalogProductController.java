package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;
import com.example.mebelshik.Service.Impl.CatalogProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/catalog")
@RestController
public class CatalogProductController {
    private final CatalogProductServiceImpl catalogProductService;

    // TODO create

    @GetMapping("/get/{id}")
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
}
