package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;
import com.example.mebelshik.Repository.CatalogRepository;
import com.example.mebelshik.Service.CatalogProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogProductServiceImpl implements CatalogProductService {

    private final CatalogRepository catalogRepository;

    @Override
    public void createCatalogProduct(CatalogProduct catalogProduct) {
        catalogRepository.save(catalogProduct);
    }

    @Override
    public CatalogProduct findCatalogProduct(Long id) throws CatalogProductNotFoundException {
        return catalogRepository.findById(id).orElseThrow(()-> new CatalogProductNotFoundException("Товар не найден"));
    }

    @Override
    public List<CatalogProduct> findCatalogProductsByCategoryId(Long category_id) {
        return catalogRepository.findAllByCategory_Id(category_id);
    }

    @Override
    public void updateCatalogProduct(CatalogProduct catalogProduct) {
        catalogRepository.save(catalogProduct);
    }

    @Override
    public void deleteCatalogProduct(Long id) {
        catalogRepository.deleteById(id);
    }
}
