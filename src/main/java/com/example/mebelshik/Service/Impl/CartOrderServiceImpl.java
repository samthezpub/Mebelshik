package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.CartOrder;
import com.example.mebelshik.Entitiy.CatalogProduct;
import com.example.mebelshik.Exception.CatalogProductNotFoundException;
import com.example.mebelshik.Repository.CartOrderRepository;
import com.example.mebelshik.Service.CartOrderService;
import com.example.mebelshik.Service.CatalogProductService;
import com.example.mebelshik.Util.TelegramSenderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartOrderServiceImpl implements CartOrderService {
    private final CartOrderRepository cartOrderRepository;
    private final CatalogProductServiceImpl catalogProductService;
    @Override
    public void createCartOrder(CartOrder cartOrder) {
        cartOrderRepository.save(cartOrder);
    }

    @Override
    public List<CatalogProduct> getCatalogProductsByProductIds(List<Long> productIds) {
        List<CatalogProduct> catalogProducts = new ArrayList<>();

        productIds.forEach(id -> {
            try {
                CatalogProduct catalogProduct = catalogProductService.findCatalogProduct(id);
                catalogProducts.add(catalogProduct);
            } catch (CatalogProductNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return catalogProducts;
    }

    @Override
    public void sendToTelegram(CartOrder cartOrder) {
        String message = "Новый заказ!\n\n"
                + "ФИО: " + cartOrder.getName() + " " + cartOrder.getSurname() + " " + cartOrder.getFathername() + "\n"
                + "Юридический статус: " + cartOrder.getUserType() + "\n"
                + "Телефон: " + cartOrder.getPhone() + "\n"
                + "Email: " + cartOrder.getEmail() + "\n"
                + "Адрес: " + cartOrder.getAddress() + "\n\n"
                + "Продукты: \n";

        Float sum = 0.f;

        for (CatalogProduct catalogProduct : cartOrder.getProducts()) {
            sum += catalogProduct.getPrice();
            message += "Название товара: " + catalogProduct.getName() + "\n"
                    + "Категория: " + catalogProduct.getCategory().getName() + "\n\n"
                    + "Цена: " + catalogProduct.getPrice() + "\n\n"
            ;
        }

        message += "Итоговая сумма товаров: " + sum + "\n";

        TelegramSenderUtil.sendMessage(message);
    }
}
