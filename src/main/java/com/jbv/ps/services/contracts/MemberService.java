package com.jbv.ps.services.contracts;

import com.jbv.ps.models.MemberDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberService {

    List<MemberDto> listUsers();
    Optional<MemberDto> getUserById(UUID id);

    MemberDto saveNewUser(MemberDto memberDto);

    void updateUserById(UUID id, MemberDto memberDto);

    void deleteUserById(UUID id);

    void updateUserPatchById(UUID id, MemberDto memberDto);
}
