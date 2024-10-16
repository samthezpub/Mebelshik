package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Entitiy.ProductFilter;

import java.util.List;

public interface ProductFilterService {
    // Поиск всех фильтров для продукта
    List<ProductFilter> findByProduct(CatalogProduct product);

    // Поиск всех продуктов с конкретным значением фильтра
    List<ProductFilter> findByFilter_IdAndValue(Long filterId, String value);
}
