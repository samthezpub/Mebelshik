package com.example.mebelshik.Entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class CartOrder {
    @Id
    @GeneratedValue
    private Long id;

    private String userType;

    private String name;
    private String surname;
    private String fathername;
    private String phone;
    private String email;
    private String address;

    @ManyToMany
    private List<CatalogProduct> products;
}
