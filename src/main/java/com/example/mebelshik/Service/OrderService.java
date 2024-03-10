package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.Order;
import com.example.mebelshik.Exception.OrderNotFoundException;

public interface OrderService {
    void createOrder(Order order);
    Order findOrder(Long id) throws OrderNotFoundException;
    void updateOrder(Order order);
    void deleteOrder(Long id);
}
