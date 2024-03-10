package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;

import java.util.List;

public interface CatalogProductService {
    void createCatalogProduct(CatalogProduct catalogProduct);
    CatalogProduct findCatalogProduct(Long id) throws CatalogProductNotFoundException;
    List<CatalogProduct> findCatalogProductsByCategoryId(Long category_id);
    void updateCatalogProduct(CatalogProduct catalogProduct);
    void deleteCatalogProduct(Long id);
}
