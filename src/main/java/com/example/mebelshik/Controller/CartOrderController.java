package com.example.mebelshik.Controller;

import com.example.mebelshik.DTO.CartOrderDto;
import com.example.mebelshik.Entitiy.CartOrder;
import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Service.CartOrderService;
import com.example.mebelshik.Service.Impl.CartOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart-order")
public class CartOrderController {
    private final CartOrderServiceImpl cartOrderService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CartOrderDto cartOrder) {
        CartOrder order = new CartOrder();
        order.setName(cartOrder.getName());
        order.setPhone(cartOrder.getPhone());
        order.setAddress(cartOrder.getAddress());
        order.setEmail(cartOrder.getEmail());
        order.setSurname(cartOrder.getSurname());
        order.setFathername(cartOrder.getFathername());
        order.setUserType(cartOrder.getUserType());

        List<CatalogProduct> products = cartOrderService.getCatalogProductsByProductIds(cartOrder.getProductIds());

        order.setProducts(products);
        System.out.println(cartOrder);

        cartOrderService.sendToTelegram(order);
        cartOrderService.createCartOrder(order);



        return new ResponseEntity<>("Успешно создан!", HttpStatus.OK);
    }
}
