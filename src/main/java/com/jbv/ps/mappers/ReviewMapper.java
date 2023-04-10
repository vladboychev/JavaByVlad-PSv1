package com.jbv.ps.mappers;

import com.jbv.ps.domain.Review;
import com.jbv.ps.models.ReviewDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review reviewDtoToReview(ReviewDto reviewDto);

    ReviewDto reviewToReviewDto(Review review);
}
