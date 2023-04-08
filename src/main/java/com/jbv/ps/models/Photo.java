package com.jbv.ps.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Photo {

    private UUID id;
    private String title;
    private String story;
    private String path;
    private Contest contest;
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
