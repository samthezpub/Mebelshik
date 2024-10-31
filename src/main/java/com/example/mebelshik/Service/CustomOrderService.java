package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.CustomOrder;

public interface CustomOrderService {
    void createCustomOrder(CustomOrder customOrder);
    void sendCustomOrderToTelegram(CustomOrder customOrder);
}
