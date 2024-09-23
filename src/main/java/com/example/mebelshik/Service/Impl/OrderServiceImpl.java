package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.Order;
import com.example.mebelshik.Enum.OrderStatus;
import com.example.mebelshik.Exception.OrderNotFoundException;
import com.example.mebelshik.Repository.OrderRepository;
import com.example.mebelshik.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order findOrder(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Заказ не найден!"));
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        try {
            Order order = findOrder(orderId);
            order.setStatus(status);

            orderRepository.save(order);
        } catch (OrderNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
