package com.sparta.controllers;

import com.sparta.domain.Image;
import com.sparta.exceptions.ImageNotFoundException;
import com.sparta.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final ImageRepository imageRepository;

    @GetMapping("/image/{id}")
    public byte[] get(@PathVariable("id") Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        return image.getImage();
    }
}
