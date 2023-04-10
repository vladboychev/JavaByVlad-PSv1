package com.jbv.ps.services.jpa;

import com.jbv.ps.mappers.PhotoMapper;
import com.jbv.ps.models.PhotoDto;
import com.jbv.ps.repositories.PhotoRepositoryJpa;
import com.jbv.ps.services.contracts.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class PhotoServiceJpa implements PhotoService {

    private final PhotoRepositoryJpa photoRepositoryJpa;
    private final PhotoMapper photoMapper;

    @Override
    public List<PhotoDto> listPhotos() {
        return photoRepositoryJpa.findAll()
                .stream()
                .map(photoMapper::photoToPhotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PhotoDto> getPhotoById(UUID id) {
        return Optional.ofNullable(photoMapper.photoToPhotoDto(photoRepositoryJpa.findById(id)
                .orElse(null)));
    }

    @Override
    public PhotoDto saveNewPhoto(PhotoDto photoDto) {
        return null;
    }

    @Override
    public void updatePhotoById(UUID id, PhotoDto photoDto) {

    }

    @Override
    public void deletePhotoById(UUID id) {

    }

    @Override
    public void updatePhotoPatchById(UUID id, PhotoDto photoDto) {

    }
}
