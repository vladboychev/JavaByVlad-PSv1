package com.jbv.ps.services.jpa;

import com.jbv.ps.mappers.ReviewMapper;
import com.jbv.ps.models.ReviewDto;
import com.jbv.ps.repositories.ReviewRepositoryJpa;
import com.jbv.ps.services.contracts.ReviewService;
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
public class ReviewServiceJpa implements ReviewService {

    private final ReviewRepositoryJpa reviewRepositoryJpa;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> listReviews() {
        return reviewRepositoryJpa.findAll()
                .stream()
                .map(reviewMapper::reviewToReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDto> getReviewById(UUID id) {
        return Optional.ofNullable(reviewMapper.reviewToReviewDto(reviewRepositoryJpa.findById(id)
                .orElse(null)));
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
