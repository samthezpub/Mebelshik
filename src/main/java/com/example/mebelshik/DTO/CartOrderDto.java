package com.example.mebelshik.DTO;

import lombok.Data;
import java.util.List;

@Data
public class CartOrderDto {
    private String userType;
    private String name;
    private String surname;
    private String fathername;
    private String phone;
    private String email;
    private String address;
    private List<Long> productIds;  // список ID товаров
}