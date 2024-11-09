package com.example.mebelshik.Entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "slug", unique = true)
    private String slug;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private List<Filter> filters = new ArrayList<>();

    private Boolean isMainCategory = false;

    @ManyToMany
    @JsonManagedReference
    private List<Category> subCategories;
}
