package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Entitiy.ProductFilter;
import com.example.mebelshik.Repository.ProductFilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductFilterServiceImpl {
    private final ProductFilterRepository productFilterRepository;

    // Получить все фильтры для продукта
    public List<ProductFilter> getFiltersForProduct(CatalogProduct product) {
        return productFilterRepository.findByProduct(product);
    }

    // Найти все продукты с конкретным значением фильтра
    public List<CatalogProduct> getProductsByFilter(Long filterId, String value) {
        return productFilterRepository.findByFilter_IdAndValue(filterId, value);
    }

    // Сохранить фильтр для продукта
    public ProductFilter saveProductFilter(ProductFilter productFilter) {
        return productFilterRepository.save(productFilter);
    }

    // Удалить фильтр продукта
    public void deleteProductFilter(Long id) {
        productFilterRepository.deleteById(id);
    }
}
