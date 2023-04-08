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
public class Contest {

    private UUID id;
    private String title;
    private String category;
    private ColorType colorType;
    private Phase phase;
    private Period phaseOneLimit;
    private Period phaseTwoLimit;
    private String coverPhotoPath;
    private User organizer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<User> juryMembers;
    private Set<User> users;
}
