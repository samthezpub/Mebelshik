package com.example.mebelshik.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TelegramSenderUtil {

    private static String botToken;
    private static String chatId;

    // Метод для инициализации токена и ID чата
    public static void initialize(String token, String chat) {
        botToken = token;
        chatId = chat;
    }

    public static void sendMessage(String message) {
        if (botToken == null || chatId == null) {
            throw new IllegalStateException("Bot token and chat ID must be initialized.");
        }

        String apiUrl = String.format("https://api.telegram.org/bot%s/sendMessage", botToken);
        String payload = String.format("chat_id=%s&text=%s", chatId, message);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Message sent successfully.");
            } else {
                System.err.println("Failed to send message. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}