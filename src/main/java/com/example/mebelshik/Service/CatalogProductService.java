package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;

import java.util.List;

public interface CatalogProductService {
    void createCatalogProduct(CatalogProduct catalogProduct);
    CatalogProduct findCatalogProduct(Long id) throws CatalogProductNotFoundException;
    CatalogProduct findCatalogProductBySlug(String slug) throws CatalogProductNotFoundException;
    List<CatalogProduct> findCatalogProductsByCategorySlug(String slug) throws CatalogProductNotFoundException;
    List<CatalogProduct> findCatalogProductsByCategoryId(Long category_id);
    List<CatalogProduct> searchByName(String name);
    void updateCatalogProduct(CatalogProduct catalogProduct);
    void deleteCatalogProduct(Long id);
}
