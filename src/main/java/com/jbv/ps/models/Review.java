package com.jbv.ps.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Review {

    private UUID id;
    private int score;
    private String comment;
    private boolean is_wrong_category;
    private Photo photo;
    private User juryMember;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
