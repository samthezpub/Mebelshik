package com.example.mebelshik.DTO;

import com.example.mebelshik.Entitiy.ProductFilter;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Float price;
    private List<String> photoURLs;
    private List<String> minPhotoURLs;

    private List<ProductFilter> productFilters;
}
