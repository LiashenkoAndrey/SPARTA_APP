package com.sparta.repositories;

import com.sparta.domain.Client;
import com.sparta.domain.Good;
import com.sparta.domain.Order;
import com.sparta.domain.dto.GoodWithAmount;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodRepository goodRepository;


    @Test
    public void save() {

        Client client = Client.builder()
                .phoneNumber("0968315205")
                .telegramId("324543535")
                .name("Tomas")
                .build();

        Good newGood = new Good(null, "dsad", "sdsda", BigDecimal.valueOf(234));
        goodRepository.save(newGood);
        Good good = goodRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        Order order = new Order();
        order.setClient(client);
        order.getGoodAmounts().add(new GoodWithAmount(good, 1));

        orderRepository.save(order);
    }
}
