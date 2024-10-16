package com.example.mebelshik.Repository;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Entitiy.ProductFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFilterRepository extends JpaRepository<ProductFilter, Long> {
    // Поиск всех фильтров для продукта
    List<ProductFilter> findByProduct(CatalogProduct product);

    // Поиск всех продуктов с конкретным значением фильтра
    @Query("SELECT p FROM CatalogProduct p JOIN p.productFilters pf WHERE pf.filter.id = :filterId AND :value MEMBER OF pf.filter.values")
    List<CatalogProduct> findByFilter_IdAndValue(Long filterId, String value);

    @Query("SELECT DISTINCT p FROM CatalogProduct p " +
            "JOIN p.productFilters pf " +
            "JOIN pf.filter f " +
            "WHERE f.id IN :filterIds AND pf.value IN :values " +
            "GROUP BY p " +
            "HAVING COUNT(DISTINCT f.id) = :filterCount")
    List<CatalogProduct> findByFilters(@Param("filterIds") List<Long> filterIds,
                                       @Param("values") List<String> values,
                                       @Param("filterCount") Long filterCount);


}
