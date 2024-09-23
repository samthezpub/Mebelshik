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

    @Column(name = "name")
    private String name;

    @ManyToOne
    private CatalogProduct catalogProduct;


    @Column(name = "prepayment")
    private Float prepayment;

    @Column(name = "expenditure")
    private Float expenditure;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date dateOfOrderAcceptance;
    private Date dateTheOrderIsReady;

    private Float remains;

}
