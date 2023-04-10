package com.jbv.ps.repositories;

import com.jbv.ps.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepositoryJpa extends JpaRepository<Photo, UUID> {
}
