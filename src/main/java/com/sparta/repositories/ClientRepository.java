package com.sparta.repositories;

import com.sparta.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByTelegramId(String telegramId);

    Boolean existsByTelegramId(String telegramId);
}
