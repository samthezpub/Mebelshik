package com.example.mebelshik.Entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class SuperCategory {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private List<Category> categories;

}
