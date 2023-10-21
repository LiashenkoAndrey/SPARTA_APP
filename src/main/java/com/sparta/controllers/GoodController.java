package com.sparta.controllers;

import com.sparta.domain.Good;
import com.sparta.repositories.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/good")
public class GoodController {

    private final GoodRepository goodRepository;

    private static final Logger log = LogManager.getLogger(GoodController.class);
    @GetMapping("/all")
    public ResponseEntity<List<Good>> getAllGoods() {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(goodRepository.findAll());

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/mark")
    public void markGood(@RequestParam("clientId") Long clientId,
                         @RequestParam("goodId") Long good_id,
                         @RequestParam("mark") Boolean mark) {

        log.info("received mark: " + clientId  + " " + good_id  +" " + mark);
        goodRepository.markGood(clientId, good_id, mark);
    }
}
