package com.jbv.ps.mappers;

import com.jbv.ps.domain.Photo;
import com.jbv.ps.models.PhotoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    Photo photoDtoToPhoto(PhotoDto photoDto);
    PhotoDto photoToPhotoDto(Photo photo);
}
