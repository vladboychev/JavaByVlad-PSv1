package com.jbv.ps.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class ReviewDto {

    private UUID id;
    private Integer version;
    private Integer score;
    private String comment;
    private Boolean is_wrong_category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
