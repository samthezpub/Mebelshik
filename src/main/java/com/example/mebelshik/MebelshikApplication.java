package com.example.mebelshik;

import com.example.mebelshik.Util.TelegramSenderUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MebelshikApplication {

    public static void main(String[] args) {
        TelegramSenderUtil.initialize("8100780786:AAEAl0FccCXYrFvFdznaI81ae0yuViNKJ4M" ,"-4569522273");
        SpringApplication.run(MebelshikApplication.class, args);
    }

}
