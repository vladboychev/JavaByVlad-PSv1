package com.jbv.ps.services;

import com.jbv.ps.models.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PhotoServiceImpl implements PhotoService {
    @Override
    public Photo getPhotoById(UUID id) {

        log.debug("Get Photo by id in PhotoService was called. Id: " + id.toString());

        return Photo.builder()
                .id(UUID.randomUUID())
                .title("My first photo")
                .story("This is the first photo I used in prime shots.")
                .build();
    }
}
