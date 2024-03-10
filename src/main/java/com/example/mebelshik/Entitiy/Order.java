package com.example.mebelshik.Entitiy;

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

    @Column(name = "price")
    private Float price;

    @Column(name = "prepayment")
    private Float prepayment;

    @Column(name = "expenditure")
    private Float expenditure;

    private String status;

    private Date dateOfOrderAcceptance;
    private Date dateTheOrderIsReady;

    private Float remains;

}
