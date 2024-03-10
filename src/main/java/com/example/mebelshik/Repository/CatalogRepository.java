package com.example.mebelshik.Repository;

import com.example.mebelshik.Entitiy.CatalogProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogProduct, Long> {
    List<CatalogProduct> findAllByCategory_Id(Long category_id);
}
