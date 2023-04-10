package com.jbv.ps.repositories;

import com.jbv.ps.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepositoryJpa extends JpaRepository<Review, UUID> {
}
