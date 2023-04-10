package com.jbv.ps.repositories;

import com.jbv.ps.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepositoryJpa extends JpaRepository<Member, UUID> {
}
