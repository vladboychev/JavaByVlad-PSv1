package com.jbv.ps.controllers;

import com.jbv.ps.models.Photo;
import com.jbv.ps.services.PhotoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class PhotoController {

    private final PhotoService photoService;

    public Photo getPhotoById(UUID id) {

        log.debug("Get Photo by id in PhotoController was called. Id: " + id.toString());

        return photoService.getPhotoById(id);
    }
}
