package com.jbv.ps.mappers;

import com.jbv.ps.domain.Member;
import com.jbv.ps.models.MemberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberDtoToMember(MemberDto memberDto);

    MemberDto memberToMemberDto(Member member);
}
