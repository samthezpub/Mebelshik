package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.Order;
import com.example.mebelshik.Exception.OrderNotFoundException;
import com.example.mebelshik.Service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {
    private final OrderServiceImpl orderService;

    // TODO create

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id){
        // Находит заказ и отправляет на сервер
        try {
            Order order = orderService.findOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
