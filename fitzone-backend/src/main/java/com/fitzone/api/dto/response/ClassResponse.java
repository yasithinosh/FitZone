package com.fitzone.api.dto.response;

import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ClassResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String intensity;
    private Long trainerId;
    private String trainerName;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private Integer durationMin;
    private Integer capacity;
    private Integer currentCount;
    private String imageUrl;
    private Boolean isActive;
}
