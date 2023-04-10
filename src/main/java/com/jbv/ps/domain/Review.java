package com.jbv.ps.domain;

import com.jbv.ps.models.PhotoDto;
import com.jbv.ps.models.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    @Version
    private Integer version;
    private Integer score;
    private String comment;
    private Boolean is_wrong_category;
    private PhotoDto photoDto;
    private UserDto juryMember;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
