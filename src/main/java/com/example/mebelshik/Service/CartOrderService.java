package com.example.mebelshik.Service;

import com.example.mebelshik.Entitiy.CartOrder;
import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;

import java.util.List;

public interface CartOrderService {
    void createCartOrder(CartOrder cartOrder);

    List<CatalogProduct> getCatalogProductsByProductIds(List<Long> productIds);

    void sendToTelegram(CartOrder cartOrder);
}
