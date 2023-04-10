package com.jbv.ps.repositories;

import com.jbv.ps.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
class ReviewRepositoryJpaTest {

    @Autowired
    ReviewRepositoryJpa reviewRepositoryJpa;

    @Test
    void testSaveReview() {
        Review savedReview = reviewRepositoryJpa.save(Review.builder()
                        .comment("newComment")
                .build());

        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isNotNull();
    }
}