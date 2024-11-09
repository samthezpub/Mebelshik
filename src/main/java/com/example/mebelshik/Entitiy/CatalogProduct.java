package com.example.mebelshik.Entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"productFilters"})
public class CatalogProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    private Category category;

    @ManyToMany
    @JsonManagedReference
    private List<Category> subCategories;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    private String descriptionSEO;

    @Column(name = "price")
    private Float price;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "title")
    private String titleSEO;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "photosCount")
    private Short photosCount;

    @Column(name = "photoURLs")
    @ElementCollection
    private List<String> photoURLs;

    @ElementCollection
    private List<String> minPhotoURLs;

    // Связь с фильтрами и их значениями
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product") // Предотвращает зацикливание
    private List<ProductFilter> productFilters;
}
