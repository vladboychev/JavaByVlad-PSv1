package com.jbv.ps.bootstrap;

import com.jbv.ps.repositories.ContestRepositoryJpa;
import com.jbv.ps.repositories.PhotoRepositoryJpa;
import com.jbv.ps.repositories.ReviewRepositoryJpa;
import com.jbv.ps.repositories.MemberRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BootstrapDataTest {

    @Autowired
    ContestRepositoryJpa contestRepositoryJpa;
    @Autowired
    PhotoRepositoryJpa photoRepositoryJpa;
    @Autowired
    ReviewRepositoryJpa reviewRepositoryJpa;
    @Autowired
    MemberRepositoryJpa memberRepositoryJpa;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(contestRepositoryJpa, photoRepositoryJpa,
                reviewRepositoryJpa, memberRepositoryJpa);
    }

    @Test
    void TestRun() throws Exception {
        bootstrapData.run(null);

        assertThat(contestRepositoryJpa.count()).isEqualTo(3);
        assertThat(photoRepositoryJpa.count()).isEqualTo(3);
        assertThat(reviewRepositoryJpa.count()).isEqualTo(3);
        assertThat(memberRepositoryJpa.count()).isEqualTo(3);
    }
}