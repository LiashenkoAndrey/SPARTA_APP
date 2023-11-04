package com.sparta.controllers;


import com.sparta.Bot;
import com.sparta.domain.Client;
import com.sparta.domain.Order;
import com.sparta.repositories.ClientRepository;
import com.sparta.repositories.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Bot bot;

    private final ClientRepository clientRepository;

    private static final Logger log = LogManager.getLogger(OrderController.class);

    private final OrderRepository orderRepository;

    @PostMapping("/new")
    public void saveNewOrder(@Valid @RequestBody Order order) throws TelegramApiException {
        log.info("received a new order: " + order);

        if (order.getClient().getId() == null) {
            Client client = clientRepository.save(order.getClient());
            order.setClient(client);
        } else {
            clientRepository.save(order.getClient());
        }

        orderRepository.save(order);

        bot.execute(SendMessage.builder()
                .text("Ваше замовлення прийняте!")
                .chatId(order.getClient().getTelegramId())
                .build());
    }

    @GetMapping("/all")
    public List<Order> getAllOrders(@RequestParam(value = "forToday", required = false, defaultValue = "false") Boolean forToday) {
        log.info("for today: " + forToday);
        if (forToday) {
            return orderRepository.findForToday();

        } else {
            return orderRepository.findAll(Sort.by("createdOn").descending());
        }
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable("id") Long id) {

        log.info("Delete order: " + id);

        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }
}
