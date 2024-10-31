package com.example.mebelshik.Entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CustomOrder {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phone;
    private String message;
}
