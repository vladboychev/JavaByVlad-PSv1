package com.jbv.ps.services;

import com.jbv.ps.models.PhotoDto;
import com.jbv.ps.services.contracts.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class PhotoServiceImpl implements PhotoService {

    private final Map<UUID, PhotoDto> photoMap;

    public PhotoServiceImpl() {
        this.photoMap = new HashMap<>();

        PhotoDto photo_Dto_one = PhotoDto.builder()
                .id(UUID.randomUUID())
                .title("My first photo")
                .story("This is the first photo I used in prime shots.")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        PhotoDto photo_Dto_two = PhotoDto.builder()
                .id(UUID.randomUUID())
                .title("My second photo")
                .story("This is the second photo I used in prime shots.")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        PhotoDto photo_Dto_three = PhotoDto.builder()
                .id(UUID.randomUUID())
                .title("My third photo")
                .story("This is the third photo I used in prime shots.")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        photoMap.put(photo_Dto_one.getId(), photo_Dto_one);
        photoMap.put(photo_Dto_two.getId(), photo_Dto_two);
        photoMap.put(photo_Dto_three.getId(), photo_Dto_three);
    }

    @Override
    public List<PhotoDto> listPhotos() {

        log.debug("List Photos in PhotoService was called.");

        return new ArrayList<>(photoMap.values());
    }

    @Override
    public Optional<PhotoDto> getPhotoById(UUID id) {

        log.debug("Get Photo by id in PhotoService was called. Id: " + id.toString());

        return Optional.of(photoMap.get(id));
    }

    @Override
    public PhotoDto saveNewPhoto(PhotoDto photoDto) {

        PhotoDto savedPhotoDto = PhotoDto.builder()
                .id(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title(photoDto.getTitle())
                .story(photoDto.getStory())
                .build();

        photoMap.put(savedPhotoDto.getId(), savedPhotoDto);

        return null;
    }

    @Override
    public void updatePhotoById(UUID id, PhotoDto photoDto) {
        PhotoDto photoDtoToUpdate = photoMap.get(id);
        photoDtoToUpdate.setTitle(photoDto.getTitle());
        photoDtoToUpdate.setStory(photoDto.getStory());
        photoDtoToUpdate.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void deletePhotoById(UUID id) {
        photoMap.remove(id);
    }

    @Override
    public void updatePhotoPatchById(UUID id, PhotoDto photoDto) {
        PhotoDto photoDtoToPatch = photoMap.get(id);

        if (StringUtils.hasText(photoDto.getTitle())) {
            photoDtoToPatch.setTitle(photoDto.getTitle());
        }
        if (StringUtils.hasText(photoDto.getStory())) {
            photoDtoToPatch.setStory(photoDto.getStory());
        }
        photoDtoToPatch.setUpdatedAt(LocalDateTime.now());
    }
}
