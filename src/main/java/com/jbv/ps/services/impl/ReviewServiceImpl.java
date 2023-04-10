package com.jbv.ps.services.impl;

import com.jbv.ps.models.ReviewDto;
import com.jbv.ps.services.contracts.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public List<ReviewDto> listReviews() {
        return null;
    }

    @Override
    public Optional<ReviewDto> getReviewById(UUID id) {
        return Optional.empty();
    }

    @Override
    public ReviewDto saveNewReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void updateReviewById(UUID id, ReviewDto reviewDto) {

    }

    @Override
    public void deleteReviewById(UUID id) {

    }

    @Override
    public void updateReviewPatchById(UUID id, ReviewDto reviewDto) {

    }
}
