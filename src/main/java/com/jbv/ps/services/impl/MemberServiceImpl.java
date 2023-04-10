package com.jbv.ps.services.impl;

import com.jbv.ps.models.MemberDto;
import com.jbv.ps.services.contracts.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final Map<UUID, MemberDto> userMap;

    public MemberServiceImpl() {

        this.userMap = new HashMap<>();

        MemberDto memberDtoOne = MemberDto.builder()
                .id(UUID.randomUUID())
                .username("firstUser")
                .firstName("First")
                .lastName("Member")
                .email("fu@ps.jbv")
                .password("firstPass")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        MemberDto memberDtoTwo = MemberDto.builder()
                .id(UUID.randomUUID())
                .username("secondUser")
                .firstName("Second")
                .lastName("Member")
                .email("su@ps.jbv")
                .password("secondPass")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        MemberDto memberDtoThree = MemberDto.builder()
                .id(UUID.randomUUID())
                .username("thirdUser")
                .firstName("Third")
                .lastName("Member")
                .email("tu@ps.jbv")
                .password("thirdPass")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userMap.put(memberDtoOne.getId(), memberDtoOne);
        userMap.put(memberDtoTwo.getId(), memberDtoTwo);
        userMap.put(memberDtoThree.getId(), memberDtoThree);
    }

    @Override
    public List<MemberDto> listUsers() {
        log.debug("List Users in MemberServiceImpl was called.");
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<MemberDto> getUserById(UUID id) {
        log.debug("Get Member by id in MemberServiceImpl was called.");
        return Optional.of(userMap.get(id));
    }

    @Override
    public MemberDto saveNewUser(MemberDto memberDto) {

        MemberDto savedMemberDto = MemberDto.builder()
                .id(UUID.randomUUID())
                .username(memberDto.getUsername())
                .firstName(memberDto.getFirstName())
                .lastName(memberDto.getLastName())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userMap.put(savedMemberDto.getId(), savedMemberDto);

        return savedMemberDto;
    }

    @Override
    public void updateUserById(UUID id, MemberDto memberDto) {
        MemberDto memberDtoToUpdate = userMap.get(id);
        memberDtoToUpdate.setUsername(memberDto.getUsername());
        memberDtoToUpdate.setEmail(memberDto.getEmail());
        memberDtoToUpdate.setFirstName(memberDto.getFirstName());
        memberDtoToUpdate.setLastName(memberDto.getLastName());
        memberDtoToUpdate.setPassword(memberDto.getPassword());
        memberDtoToUpdate.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void deleteUserById(UUID id) {
        userMap.remove(id);
    }

    @Override
    public void updateUserPatchById(UUID id, MemberDto memberDto) {
        MemberDto memberDtoToPatch = userMap.get(id);

        if (StringUtils.hasText(memberDto.getUsername())) {
            memberDtoToPatch.setUsername(memberDto.getUsername());
        }
        if (StringUtils.hasText(memberDto.getEmail())) {
            memberDtoToPatch.setEmail(memberDto.getEmail());
        }
        if (StringUtils.hasText(memberDto.getFirstName())) {
            memberDtoToPatch.setFirstName(memberDto.getFirstName());
        }
        if (StringUtils.hasText(memberDto.getLastName())) {
            memberDtoToPatch.setLastName(memberDto.getLastName());
        }
        if (StringUtils.hasText(memberDto.getPassword())) {
            memberDtoToPatch.setPassword(memberDto.getPassword());
        }
    }
}
