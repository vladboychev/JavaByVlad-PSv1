package com.jbv.ps.domain;

import com.jbv.ps.enums.ContestType;
import com.jbv.ps.enums.Phase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.time.Period;
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
    private ContestType contestType;
    private Phase phase;
    private Period phaseOneLimit;
    private Period phaseTwoLimit;
    private String coverPhotoPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //    private MemberDto organizer;
//    private Set<MemberDto> juryMembers;
//    private Set<MemberDto> users;
}
