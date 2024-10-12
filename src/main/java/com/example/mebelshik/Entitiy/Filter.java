package com.example.mebelshik.Entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "filters")
public class Filter {
    // Геттеры и сеттеры
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filterType; // Тип фильтра, например "frame" или "color"

    @ElementCollection
    @CollectionTable(name = "filter_values", joinColumns = @JoinColumn(name = "filter_id"))
    @Column(name = "value")
    private List<String> values; // Список значений фильтра

}