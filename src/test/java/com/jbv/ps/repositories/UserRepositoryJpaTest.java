package com.jbv.ps.repositories;

import com.jbv.ps.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class UserRepositoryJpaTest {

    @Autowired
    UserRepositoryJpa userRepositoryJpa;

    @Test
    void testSaveUser() {
        User savedUser = userRepositoryJpa.save(User.builder()
                        .username("testUser")
                .build());

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }
}