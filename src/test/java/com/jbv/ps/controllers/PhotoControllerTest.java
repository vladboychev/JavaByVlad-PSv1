package com.jbv.ps.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhotoControllerTest {

    @Autowired
    PhotoController photoController;

    @Test
    void getPhotoById() {
        System.out.println(photoController.getPhotoById(UUID.randomUUID()));
    }
}