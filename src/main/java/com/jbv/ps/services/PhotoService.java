package com.jbv.ps.services;

import com.jbv.ps.models.Photo;

import java.util.UUID;

public interface PhotoService {

    Photo getPhotoById(UUID id);
}
