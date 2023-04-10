package com.jbv.ps.repositories;

import com.jbv.ps.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<User, UUID> {
}
