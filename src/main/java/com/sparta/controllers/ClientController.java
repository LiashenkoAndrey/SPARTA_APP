package com.sparta.controllers;

import com.sparta.domain.Client;
import com.sparta.exceptions.ClientNotFoundException;
import com.sparta.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientRepository clientRepository;

    private static final Logger log = LogManager.getLogger(ClientRepository.class);

    @CrossOrigin(origins = "*")
    @GetMapping
    public Client getClientByTelegramId(@RequestParam("telegramId") String telegramId) {
        log.info(telegramId);
        return clientRepository.findClientByTelegramId(telegramId)
                .orElseThrow(ClientNotFoundException::new);
    }
}
