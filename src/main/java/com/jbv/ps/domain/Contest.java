package com.jbv.ps.domain;

import com.jbv.ps.enums.Phase;
import com.jbv.ps.models.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.swing.plaf.synth.ColorType;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contest {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    @Version
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
