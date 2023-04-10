package com.jbv.ps.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbv.ps.models.PhotoDto;
import com.jbv.ps.services.contracts.PhotoService;
import com.jbv.ps.services.PhotoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhotoController.class)
class PhotoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PhotoService photoService;

    PhotoServiceImpl photoServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<PhotoDto> photoArgumentCaptor;

    @BeforeEach
    void setUp() {
        photoServiceImpl = new PhotoServiceImpl();
    }

    @Test
    void testListPhotos() throws Exception {
        given(photoService.listPhotos()).willReturn(photoServiceImpl.listPhotos());

        mockMvc.perform(get(PhotoController.PATH_TO_PHOTOS)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getPhotoById() throws Exception {
        PhotoDto testPhotoDto = photoServiceImpl.listPhotos().get(0);

        given(photoService.getPhotoById(testPhotoDto.getId())).willReturn(Optional.of(testPhotoDto));

        mockMvc.perform(get(PhotoController.PATH_TO_PHOTOS_ID, testPhotoDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testPhotoDto.getId().toString())))
                .andExpect(jsonPath("$.title", is(testPhotoDto.getTitle())));
    }

    @Test
    void testGetPhotoByIdNotFound() throws Exception {

        given(photoService.getPhotoById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(PhotoController.PATH_TO_PHOTOS_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePhoto() throws Exception {
        PhotoDto testPhotoDto = photoServiceImpl.listPhotos().get(0);
        testPhotoDto.setId(null);

        given(photoService.saveNewPhoto(any(PhotoDto.class))).willReturn(photoServiceImpl.listPhotos().get(1));

        mockMvc.perform(post(PhotoController.PATH_TO_PHOTOS)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPhotoDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testUpdatePhoto() throws Exception {
        PhotoDto testPhotoDto = photoServiceImpl.listPhotos().get(0);

        mockMvc.perform(put(PhotoController.PATH_TO_PHOTOS_ID, testPhotoDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPhotoDto)))
                .andExpect(status().isNoContent());

        verify(photoService).updatePhotoById(any(UUID.class), any(PhotoDto.class));
    }

    @Test
    void testDeletePhoto() throws Exception {
        PhotoDto testPhotoDto = photoServiceImpl.listPhotos().get(0);

        mockMvc.perform(delete(PhotoController.PATH_TO_PHOTOS_ID, testPhotoDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(photoService).deletePhotoById(uuidArgumentCaptor.capture());

        assertThat(testPhotoDto.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testPatchPhoto() throws Exception {
        PhotoDto testPhotoDto = photoServiceImpl.listPhotos().get(0);

        Map<String, Object> photoMap = new HashMap<>();
        photoMap.put("title", "what title");

        mockMvc.perform(patch(PhotoController.PATH_TO_PHOTOS_ID, testPhotoDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(photoMap)))
                .andExpect(status().isNoContent());

        verify(photoService).updatePhotoPatchById(uuidArgumentCaptor.capture(), photoArgumentCaptor.capture());

        assertThat(testPhotoDto.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(photoMap.get("title")).isEqualTo(photoArgumentCaptor.getValue().getTitle());
     }
}