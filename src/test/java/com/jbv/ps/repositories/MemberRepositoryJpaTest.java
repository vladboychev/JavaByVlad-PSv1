package com.jbv.ps.repositories;

import com.jbv.ps.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryJpaTest {

    @Autowired
    MemberRepositoryJpa memberRepositoryJpa;

    @Test
    void testSaveUser() {
        Member savedMember = memberRepositoryJpa.save(Member.builder()
                        .username("testUser")
                .build());

        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getId()).isNotNull();
    }
}