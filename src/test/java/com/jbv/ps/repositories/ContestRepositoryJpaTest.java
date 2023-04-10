package com.jbv.ps.repositories;

import com.jbv.ps.domain.Contest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ContestRepositoryJpaTest {

    @Autowired
    ContestRepositoryJpa contestRepositoryJpa;

    @Test
    void testSaveContest() {
        Contest savedContest = contestRepositoryJpa.save(Contest.builder()
                        .title("testTitle")
                .build());

        assertThat(savedContest).isNotNull();
        assertThat(savedContest.getId()).isNotNull();
    }
}