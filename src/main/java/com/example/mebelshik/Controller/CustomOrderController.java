package com.example.mebelshik.Controller;

import com.example.mebelshik.Entitiy.CustomOrder;
import com.example.mebelshik.Entitiy.Order;
import com.example.mebelshik.Service.Impl.CustomOrderServiceImpl;
import com.example.mebelshik.Service.Impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customorder")
public class CustomOrderController {
    private final CustomOrderServiceImpl customOrderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CustomOrder order) {
        customOrderService.createCustomOrder(order);
        customOrderService.sendCustomOrderToTelegram(order);

        return ResponseEntity.ok().build();
    }
}
