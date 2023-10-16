package com.sparta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class SpartaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaApplication.class, args);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot("6449858894:AAF5ZgcIY-mWFH_uAcoUXgvhIEnhqJEykRM"));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
