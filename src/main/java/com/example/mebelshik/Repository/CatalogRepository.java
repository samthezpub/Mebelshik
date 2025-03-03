package com.example.mebelshik.Repository;

import com.example.mebelshik.Entitiy.CatalogProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogProduct, Long> {
    List<CatalogProduct> findAllByCategory_Id(Long category_id);


    boolean existsBySlug(String slug);
    List<CatalogProduct> findCatalogProductsByCategorySlug(String slug);
    Optional<CatalogProduct> findCatalogProductBySlug(String slug);
    List<CatalogProduct> findByNameContainingIgnoreCase(String name);
}
