package com.sparta;

import com.sparta.repositories.ClientRepository;
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

    @Override
    public void onUpdateReceived(Update update) {
        try {
            var message = update.getMessage();
            var appInfo = new WebAppInfo("https://c8bd-185-244-159-203.ngrok-free.app?telegramId="+ message.getFrom().getId());

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
