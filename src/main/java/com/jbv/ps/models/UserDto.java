package com.jbv.ps.models;

import com.jbv.ps.enums.Rating;
import com.jbv.ps.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class UserDto {

    private UUID id;
    private Integer version;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String firstName;
    private String lastName;
    private Role role;
    private Rating rating;
    private Integer points;
    private Set<ContestDto> juryContests;
    private Set<ContestDto> participatedContests;
}
