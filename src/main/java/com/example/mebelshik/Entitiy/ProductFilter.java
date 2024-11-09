package com.example.mebelshik.Entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_filters")
@JsonIgnoreProperties({"product"}) // Игнорируем product при сериализации
public class ProductFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Связь с продуктом
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    @JsonIgnoreProperties("productFilters")
    private CatalogProduct product;

    // Связь с фильтром
    @ManyToOne
    @JoinColumn(name = "filter_id", nullable = false)
    @JsonBackReference
    private Filter filter;

    // Значение выбранного фильтра
    @Column(name = "value", nullable = false)
    private String value;
}