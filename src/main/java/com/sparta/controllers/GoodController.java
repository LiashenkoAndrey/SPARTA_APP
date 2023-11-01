package com.sparta.controllers;

import com.sparta.domain.Good;
import com.sparta.domain.GoodMark;
import com.sparta.domain.Image;
import com.sparta.domain.dto.Mark;
import com.sparta.exceptions.ClientNotFoundException;
import com.sparta.repositories.ClientRepository;
import com.sparta.repositories.GoodMarkRepository;
import com.sparta.repositories.GoodRepository;
import com.sparta.repositories.ImageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/good")
public class GoodController {

    private final ClientRepository clientRepository;

    private final GoodMarkRepository goodMarkRepository;

    private final GoodRepository goodRepository;

    private final ImageRepository imageRepository;

    private static final Logger log = LogManager.getLogger(GoodController.class);

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<Good> getAllGoods() {
        return goodRepository.findAll();

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/new")
    public void newGood(
            @RequestParam("image") MultipartFile file,
            @RequestParam("price") BigDecimal price,
            @RequestParam("name") String name) throws IOException {

        log.info("Received a new Good, " + price + ", " + name + ", file mot empty= " + !file.isEmpty());

        Image image = imageRepository.save(new Image(file.getBytes()));

        log.info("saved image id: " + image.getId());
        Good good = goodRepository.save(Good.builder()
                        .imageId(image.getId())
                        .name(name)
                        .price(price)
                .build());

        log.info("saved good: " + good);
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
