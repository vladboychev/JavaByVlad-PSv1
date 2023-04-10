package com.jbv.ps.services.contracts;

import com.jbv.ps.models.ReviewDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewService {

    List<ReviewDto> listReviews();
    Optional<ReviewDto> getReviewById(UUID id);

    ReviewDto saveNewReview(ReviewDto reviewDto);

    void updateReviewById(UUID id, ReviewDto reviewDto);

    void deleteReviewById(UUID id);

    void updateReviewPatchById(UUID id, ReviewDto reviewDto);
}
