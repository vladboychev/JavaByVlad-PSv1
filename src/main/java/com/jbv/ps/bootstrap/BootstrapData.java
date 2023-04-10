package com.jbv.ps.bootstrap;

import com.jbv.ps.domain.Contest;
import com.jbv.ps.domain.Member;
import com.jbv.ps.domain.Photo;
import com.jbv.ps.domain.Review;
import com.jbv.ps.enums.ContestType;
import com.jbv.ps.enums.Phase;
import com.jbv.ps.repositories.ContestRepositoryJpa;
import com.jbv.ps.repositories.PhotoRepositoryJpa;
import com.jbv.ps.repositories.ReviewRepositoryJpa;
import com.jbv.ps.repositories.MemberRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ContestRepositoryJpa contestRepositoryJpa;
    private final PhotoRepositoryJpa photoRepositoryJpa;
    private final ReviewRepositoryJpa reviewRepositoryJpa;
    private final MemberRepositoryJpa memberRepositoryJpa;

    @Override
    public void run(String... args) throws Exception {
        loadContestData();
        loadPhotoData();
        loadReviewData();
        loadUserData();
    }

    private void loadContestData() {
        if (contestRepositoryJpa.count() == 0) {
            Contest contestOne = Contest.builder()
                    .title("Contest One")
                    .category("Category")
                    .contestType(ContestType.OPEN)
                    .coverPhotoPath("urlOne")
                    .phase(Phase.PHASE_I)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Contest contestTwo = Contest.builder()
                    .title("Contest Two")
                    .category("Category")
                    .contestType(ContestType.OPEN)
                    .coverPhotoPath("urlTwo")
                    .phase(Phase.PHASE_I)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Contest contestThree = Contest.builder()
                    .title("Contest Three")
                    .category("Category")
                    .contestType(ContestType.OPEN)
                    .coverPhotoPath("urlThree")
                    .phase(Phase.PHASE_I)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            contestRepositoryJpa.save(contestOne);
            contestRepositoryJpa.save(contestTwo);
            contestRepositoryJpa.save(contestThree);
        }
    }

    private void loadPhotoData() {
        if (photoRepositoryJpa.count() == 0) {
            Photo photo_one = Photo.builder()
                    .title("My first photo")
                    .story("This is the first photo I used in prime shots.")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Photo photo_two = Photo.builder()
                    .title("My second photo")
                    .story("This is the second photo I used in prime shots.")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Photo photo_three = Photo.builder()
                    .title("My third photo")
                    .story("This is the third photo I used in prime shots.")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            photoRepositoryJpa.save(photo_one);
            photoRepositoryJpa.save(photo_two);
            photoRepositoryJpa.save(photo_three);
        }
    }

    private void loadReviewData() {
        if (reviewRepositoryJpa.count() == 0) {
            Review reviewOne = Review.builder()
                    .comment("Comment One")
                    .score(1)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Review reviewTwo = Review.builder()
                    .comment("Comment Two")
                    .score(2)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Review reviewThree = Review.builder()
                    .comment("Comment Three")
                    .score(3)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            reviewRepositoryJpa.save(reviewOne);
            reviewRepositoryJpa.save(reviewTwo);
            reviewRepositoryJpa.save(reviewThree);
        }
    }

    private void loadUserData() {
        if (memberRepositoryJpa.count() == 0) {
            Member memberOne = Member.builder()
                    .username("firstUser")
                    .firstName("First")
                    .lastName("Member")
                    .email("fu@ps.jbv")
                    .password("firstPass")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Member memberTwo = Member.builder()
                    .username("secondUser")
                    .firstName("Second")
                    .lastName("Member")
                    .email("su@ps.jbv")
                    .password("secondPass")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Member memberThree = Member.builder()
                    .username("thirdUser")
                    .firstName("Third")
                    .lastName("Member")
                    .email("tu@ps.jbv")
                    .password("thirdPass")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            memberRepositoryJpa.save(memberOne);
            memberRepositoryJpa.save(memberTwo);
            memberRepositoryJpa.save(memberThree);
        }
    }
}
