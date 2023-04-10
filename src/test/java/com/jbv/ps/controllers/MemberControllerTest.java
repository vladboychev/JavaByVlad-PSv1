package com.jbv.ps.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbv.ps.models.MemberDto;
import com.jbv.ps.services.contracts.MemberService;
import com.jbv.ps.services.impl.MemberServiceImpl;
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

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    MemberServiceImpl userServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<MemberDto> userArgumentCaptor;

    @BeforeEach
    void setUp() {
        userServiceImpl = new MemberServiceImpl();
    }

    @Test
    void testListUsers() throws Exception {
        given(memberService.listUsers()).willReturn(userServiceImpl.listUsers());

        mockMvc.perform(get(MemberController.PATH_TO_USERS)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getUserById() throws Exception {
        MemberDto testMemberDto = userServiceImpl.listUsers().get(0);

        given(memberService.getUserById(testMemberDto.getId())).willReturn(Optional.of(testMemberDto));

        mockMvc.perform(get(MemberController.PATH_TO_USERS_ID, testMemberDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testMemberDto.getId().toString())))
                .andExpect(jsonPath("$.username", is(testMemberDto.getUsername())));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {

        given(memberService.getUserById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(MemberController.PATH_TO_USERS_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser() throws Exception {
        MemberDto testMemberDto = userServiceImpl.listUsers().get(0);
        testMemberDto.setId(null);

        given(memberService.saveNewUser(any(MemberDto.class))).willReturn(userServiceImpl.listUsers().get(1));

        mockMvc.perform(post(MemberController.PATH_TO_USERS)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMemberDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testUpdateUser() throws Exception {
        MemberDto testMemberDto = userServiceImpl.listUsers().get(0);

        mockMvc.perform(put(MemberController.PATH_TO_USERS_ID, testMemberDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMemberDto)))
                .andExpect(status().isNoContent());

        verify(memberService).updateUserById(any(UUID.class), any(MemberDto.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        MemberDto testMemberDto = userServiceImpl.listUsers().get(0);

        mockMvc.perform(delete(MemberController.PATH_TO_USERS_ID, testMemberDto.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(memberService).deleteUserById(uuidArgumentCaptor.capture());

        assertThat(testMemberDto.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testPatchUser() throws Exception {
        MemberDto testMemberDto = userServiceImpl.listUsers().get(0);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", "What Name");

        mockMvc.perform(patch(MemberController.PATH_TO_USERS_ID, testMemberDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMap)))
                .andExpect(status().isNoContent());

        verify(memberService).updateUserPatchById(uuidArgumentCaptor.capture(), userArgumentCaptor.capture());

        assertThat(testMemberDto.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(userMap.get("username")).isEqualTo(userArgumentCaptor.getValue().getUsername());
    }
}