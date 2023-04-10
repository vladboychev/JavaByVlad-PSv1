package com.jbv.ps.models;

import com.jbv.ps.enums.Phase;
import lombok.Builder;
import lombok.Data;

import javax.swing.plaf.synth.ColorType;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
public class ContestDto {

    private UUID id;
    private Integer version;
    private String title;
    private String category;
    private ColorType colorType;
    private Phase phase;
    private Period phaseOneLimit;
    private Period phaseTwoLimit;
    private String coverPhotoPath;
    private UserDto organizer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<UserDto> juryMembers;
    private Set<UserDto> users;
}
