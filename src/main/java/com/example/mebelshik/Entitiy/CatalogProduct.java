package com.example.mebelshik.Entitiy;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CatalogProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;
}
