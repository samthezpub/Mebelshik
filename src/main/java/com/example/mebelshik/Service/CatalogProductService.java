package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;
import com.example.mebelshik.Exception.CategoryNotFoundException;

import java.util.List;

public interface CatalogProductService {
    void createCatalogProduct(CatalogProduct catalogProduct);
    List<CatalogProduct> findAll();
    CatalogProduct findCatalogProduct(Long id) throws CatalogProductNotFoundException;
    CatalogProduct findCatalogProductBySlug(String slug) throws CatalogProductNotFoundException;
    List<CatalogProduct> findCatalogProductsByCategorySlug(String slug) throws CatalogProductNotFoundException, CategoryNotFoundException;
    List<CatalogProduct> findCatalogProductsByCategoryId(Long category_id);
    List<CatalogProduct> searchByName(String name);
    void updateCatalogProduct(CatalogProduct catalogProduct);
    void deleteCatalogProduct(Long id);
    String generateKeywords(String name);
}
