package com.sparta;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    private static final Logger log = LogManager.getLogger(Bot.class);

//    private static final String HOST = "https://sparta.miloverada.gov.ua:3000";
    private static final String HOST = "https://f241-185-244-159-248.ngrok-free.app";
    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        var id = message.getFrom().getId();
        try {
            if (id == 1053183238L) {
                execute(SendMessage.builder()
                        .chatId(message.getChatId())
                        .text("Обери дію")
                        .replyMarkup(new InlineKeyboardMarkup(List.of(
                                List.of(
                                        InlineKeyboardButton.builder()
                                                .text("Переглянути замовлення \uD83D\uDCDD")
                                                .webApp(new WebAppInfo(HOST + "/orders"))
                                                .build()
                                ),
                                List.of(
                                        InlineKeyboardButton.builder()
                                                .text("Додати нову позицію \uD83D\uDC1F ")
                                                .webApp(new WebAppInfo(HOST + "/newGood"))
                                                .build()
                                )
                        )))
                        .build());
            }

                var appInfo = new WebAppInfo(HOST);

                execute(SendMessage.builder()
                        .chatId(update.getMessage().getChatId())
                        .replyMarkup(new InlineKeyboardMarkup(List.of(
                                List.of(
                                        InlineKeyboardButton.builder()
                                                .text("Зробити замовлення")
                                                .webApp(appInfo)
                                                .build()
                                )
                        )))
                        .text("Давайте почнемо \uD83C\uDF63 \n\n Натисніть кнопку нижче, щоб замовити ідеальний обід")
                        .build());


        } catch (TelegramApiException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotToken() {
        return "6449858894:AAF5ZgcIY-mWFH_uAcoUXgvhIEnhqJEykRM";
    }

    @Override
    public String getBotUsername() {
        return "SPARTA";
    }
}
