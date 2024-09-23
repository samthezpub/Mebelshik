package com.example.mebelshik.Entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "slug", unique = true)
    private String slug;



    @ManyToMany
    private List<Category> subCategories;
}
