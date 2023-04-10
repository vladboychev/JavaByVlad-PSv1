package com.jbv.ps.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class PhotoDto {

    private UUID id;
    private Integer version;
    private String title;
    private String story;
    private String path;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
