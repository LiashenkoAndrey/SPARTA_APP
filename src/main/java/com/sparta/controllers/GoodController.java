package com.sparta.controllers;

import com.sparta.domain.Good;
import com.sparta.domain.GoodMark;
import com.sparta.domain.dto.Mark;
import com.sparta.exceptions.ClientNotFoundException;
import com.sparta.repositories.ClientRepository;
import com.sparta.repositories.GoodMarkRepository;
import com.sparta.repositories.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/good")
public class GoodController {

    private final ClientRepository clientRepository;

    private final GoodMarkRepository goodMarkRepository;

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

        GoodMark goodMark = goodMarkRepository.getGoodMarksByClientIdAndGoodId(clientId, good_id);

        if (goodMark != null) {
            goodMark.setMark(mark);
            goodMarkRepository.save(goodMark);
        } else {
            goodMarkRepository.save(new GoodMark(good_id, clientId, mark));
        }
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/getMarks")
    public List<GoodMark> getGoodsMarksByClientId(@RequestParam("clientId") Long clientId) {
        if (clientRepository.existsById(clientId)) {
            return goodMarkRepository.getGoodMarksByClientId(clientId);
        } else throw new ClientNotFoundException("Client with id: " + clientId+ " not found!");
    }
}
