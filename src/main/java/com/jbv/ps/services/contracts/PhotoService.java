package com.jbv.ps.services.contracts;

import com.jbv.ps.models.PhotoDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhotoService {

    List<PhotoDto> listPhotos();

    Optional<PhotoDto> getPhotoById(UUID id);

    PhotoDto saveNewPhoto(PhotoDto photoDto);

    void updatePhotoById(UUID id, PhotoDto photoDto);

    void deletePhotoById(UUID id);

    void updatePhotoPatchById(UUID id, PhotoDto photoDto);
}
