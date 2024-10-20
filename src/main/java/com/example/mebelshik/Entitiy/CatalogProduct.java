package com.example.mebelshik.Entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class CatalogProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Category> subCategories;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "title")
    private String titleSEO;

    @Column(name = "keywords")
    @ElementCollection
    private List<String> keywords;

    @Column(name = "photosCount")
    private Short photosCount;

    @Column(name = "photoURLs")
    @ElementCollection
    private List<String> photoURLs;

    @ElementCollection
    private List<String> minPhotoURLs;

    // Связь с фильтрами и их значениями
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductFilter> productFilters;
}
