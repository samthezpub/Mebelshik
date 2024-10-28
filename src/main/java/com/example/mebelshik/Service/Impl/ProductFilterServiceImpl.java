package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Entitiy.Filter;
import com.example.mebelshik.Entitiy.ProductFilter;
import com.example.mebelshik.Repository.CatalogRepository;
import com.example.mebelshik.Repository.FilterRepository;
import com.example.mebelshik.Repository.ProductFilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductFilterServiceImpl {
    private final ProductFilterRepository productFilterRepository;
    private final CatalogRepository catalogRepository;
    private final FilterRepository filterRepository;

    // Получить все фильтры для продукта
    public List<ProductFilter> getFiltersForProduct(CatalogProduct product) {
        return productFilterRepository.findByProduct(product);
    }

    // Создать новый ProductFilter по productId, filterId и value
    public ProductFilter createProductFilter(Long productId, Long filterId, String value) {
        CatalogProduct product = catalogRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден с id: " + productId));
        Filter filter = filterRepository.findById(filterId)
                .orElseThrow(() -> new IllegalArgumentException("Фильтр не найден с id: " + filterId));

        ProductFilter productFilter = new ProductFilter();
        productFilter.setProduct(product);
        productFilter.setFilter(filter);
        productFilter.setValue(value);

        return productFilterRepository.save(productFilter);
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
