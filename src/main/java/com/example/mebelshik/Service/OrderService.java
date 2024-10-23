package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.Order;
import com.example.mebelshik.Enum.OrderStatus;
import com.example.mebelshik.Exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    Order findOrder(Long id) throws OrderNotFoundException;
    List<Order> findAllOrders();
    void updateOrder(Order order);
    void updateOrderStatus(Long orderId, OrderStatus status);
    void deleteOrder(Long id);
}
