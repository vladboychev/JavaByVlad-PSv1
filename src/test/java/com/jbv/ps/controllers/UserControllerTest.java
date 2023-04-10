package com.jbv.ps.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbv.ps.models.UserDto;
import com.jbv.ps.services.contracts.UserService;
import com.jbv.ps.services.UserServiceImpl;
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

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    UserServiceImpl userServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<UserDto> userArgumentCaptor;

    @BeforeEach
    void setUp() {
        userServiceImpl = new UserServiceImpl();
    }

    @Test
    void testListUsers() throws Exception {
        given(userService.listUsers()).willReturn(userServiceImpl.listUsers());

        mockMvc.perform(get(UserController.PATH_TO_USERS)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getUserById() throws Exception {
        UserDto testUserDto = userServiceImpl.listUsers().get(0);

        given(userService.getUserById(testUserDto.getId())).willReturn(Optional.of(testUserDto));

        mockMvc.perform(get(UserController.PATH_TO_USERS_ID, testUserDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testUserDto.getId().toString())))
                .andExpect(jsonPath("$.username", is(testUserDto.getUsername())));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {

        given(userService.getUserById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(UserController.PATH_TO_USERS_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto testUserDto = userServiceImpl.listUsers().get(0);
        testUserDto.setId(null);

        given(userService.saveNewUser(any(UserDto.class))).willReturn(userServiceImpl.listUsers().get(1));

        mockMvc.perform(post(UserController.PATH_TO_USERS)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserDto testUserDto = userServiceImpl.listUsers().get(0);

        mockMvc.perform(put(UserController.PATH_TO_USERS_ID, testUserDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUserDto)))
                .andExpect(status().isNoContent());

        verify(userService).updateUserById(any(UUID.class), any(UserDto.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        UserDto testUserDto = userServiceImpl.listUsers().get(0);

        mockMvc.perform(delete(UserController.PATH_TO_USERS_ID, testUserDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userService).deleteUserById(uuidArgumentCaptor.capture());

        assertThat(testUserDto.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testPatchUser() throws Exception {
        UserDto testUserDto = userServiceImpl.listUsers().get(0);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", "What Name");

        mockMvc.perform(patch(UserController.PATH_TO_USERS_ID, testUserDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMap)))
                .andExpect(status().isNoContent());

        verify(userService).updateUserPatchById(uuidArgumentCaptor.capture(), userArgumentCaptor.capture());

        assertThat(testUserDto.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(userMap.get("username")).isEqualTo(userArgumentCaptor.getValue().getUsername());
    }
}