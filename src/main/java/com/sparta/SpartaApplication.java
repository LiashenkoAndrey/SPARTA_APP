package com.sparta;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@PropertySource("classpath:dev_application.properties")
@SpringBootApplication
@RequiredArgsConstructor
public class SpartaApplication {

    private final ApplicationContext context;

    @Bean
    public TelegramBotsApi getTelegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(context.getBean(Bot.class));
        return telegramBotsApi;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpartaApplication.class, args);
    }

}
