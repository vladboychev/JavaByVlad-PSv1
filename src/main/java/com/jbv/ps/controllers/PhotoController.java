package com.jbv.ps.controllers;

import com.jbv.ps.exceptions.NotFoundException;
import com.jbv.ps.models.PhotoDto;
import com.jbv.ps.services.contracts.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PhotoController {

    public static final String PATH_TO_PHOTOS = "/api/v1/photos";
    public static final String PATH_TO_PHOTOS_ID = PATH_TO_PHOTOS + "/{id}";

    private final PhotoService photoService;

    @GetMapping(PATH_TO_PHOTOS)
    public List<PhotoDto> listPhotos() {

        log.debug("List Photos in PhotoController was called.");

        return photoService.listPhotos();
    }

    @GetMapping(PATH_TO_PHOTOS_ID)
    public PhotoDto getPhotoById(@PathVariable("id") UUID id) {

        log.debug("Get Photo by id in PhotoController was called. Id: " + id.toString());

        return photoService.getPhotoById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(PATH_TO_PHOTOS)
    public ResponseEntity handlePost(@RequestBody PhotoDto photoDto) {

        PhotoDto savedPhotoDto = photoService.saveNewPhoto(photoDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location","/api/v1/photos/" + savedPhotoDto.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(PATH_TO_PHOTOS_ID)
    public ResponseEntity updatePhotoById(@PathVariable("id") UUID id, @RequestBody PhotoDto photoDto) {

        photoService.updatePhotoById(id, photoDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(PATH_TO_PHOTOS_ID)
    public ResponseEntity deletePhotoById(@PathVariable("id") UUID id) {
        photoService.deletePhotoById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(PATH_TO_PHOTOS_ID)
    public ResponseEntity updatePhotoPatchById(@PathVariable("id") UUID id, @RequestBody PhotoDto photoDto) {
        photoService.updatePhotoPatchById(id, photoDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
