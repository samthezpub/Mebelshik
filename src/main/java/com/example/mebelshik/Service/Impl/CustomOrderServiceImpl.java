package com.example.mebelshik.Service.Impl;

import com.example.mebelshik.Entitiy.CustomOrder;
import com.example.mebelshik.Repository.CustomOrderRepository;
import com.example.mebelshik.Service.CustomOrderService;
import com.example.mebelshik.Util.TelegramSenderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOrderServiceImpl implements CustomOrderService {

    private final CustomOrderRepository customOrderRepository;

    @Override
    public void createCustomOrder(CustomOrder customOrder) {
        customOrderRepository.save(customOrder);
    }

    @Override
    public void sendCustomOrderToTelegram(CustomOrder customOrder) {
        String message = "Поступила новая заявка на консультацию по мебели на заказ:\n"
                + "Имя: " + customOrder.getName() + "\n"
                + "Телефон: " + customOrder.getPhone() + "\n"
                + "Сообщение: " + customOrder.getMessage() + "\n";

        TelegramSenderUtil.sendMessage(message);
    }
}
