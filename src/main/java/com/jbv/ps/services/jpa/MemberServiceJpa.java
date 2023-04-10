package com.jbv.ps.services.jpa;

import com.jbv.ps.mappers.MemberMapper;
import com.jbv.ps.models.MemberDto;
import com.jbv.ps.repositories.MemberRepositoryJpa;
import com.jbv.ps.services.contracts.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class MemberServiceJpa implements MemberService {

    private final MemberRepositoryJpa memberRepositoryJpa;
    private final MemberMapper memberMapper;

    @Override
    public List<MemberDto> listUsers() {
        return memberRepositoryJpa.findAll()
                .stream()
                .map(memberMapper::memberToMemberDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberDto> getUserById(UUID id) {
        return Optional.ofNullable(memberMapper.memberToMemberDto(memberRepositoryJpa.findById(id)
                .orElse(null)));
    }

    @Override
    public MemberDto saveNewUser(MemberDto memberDto) {
        return null;
    }

    @Override
    public void updateUserById(UUID id, MemberDto memberDto) {

    }

    @Override
    public void deleteUserById(UUID id) {

    }

    @Override
    public void updateUserPatchById(UUID id, MemberDto memberDto) {

    }
}
