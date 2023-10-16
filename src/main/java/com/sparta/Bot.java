package com.sparta;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .replyMarkup(new InlineKeyboardMarkup(List.of(
                            List.of(
                                    InlineKeyboardButton.builder()
                                            .text("Зробити замовлення")
                                            .webApp(new WebAppInfo("https://www.google.com/"))
                                            .build()
                            )
                    )))
                    .text("Hello World")
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public Bot(String botToken) {
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        return "SPARTA";
    }
}
