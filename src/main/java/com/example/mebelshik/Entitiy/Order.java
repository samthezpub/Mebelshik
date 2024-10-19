package com.example.mebelshik.Entitiy;

import com.example.mebelshik.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Table(name="\"order\"")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Float price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date dateOfOrderAcceptance;
}
