package com.example.mebelshik.Entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Category> categories;

}
