package com.jbv.ps.repositories;

import com.jbv.ps.domain.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PhotoRepositoryJpaTest {

    @Autowired
    PhotoRepositoryJpa photoRepositoryJpa;

    @Test
    void testSavePhoto() {
        Photo savedPhoto = photoRepositoryJpa.save(Photo.builder()
                        .title("testTitle")
                .build());

        assertThat(savedPhoto).isNotNull();
        assertThat(savedPhoto.getId()).isNotNull();
    }
}