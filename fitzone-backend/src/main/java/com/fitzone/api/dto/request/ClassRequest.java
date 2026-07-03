package com.fitzone.api.dto.request;

import com.fitzone.api.model.ClassCategory;
import com.fitzone.api.model.ClassIntensity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class ClassRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "Category is required")
    private ClassCategory category;

    @NotNull(message = "Intensity is required")
    private ClassIntensity intensity;

    private Long trainerId;

    private DayOfWeek dayOfWeek;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "Duration is required")
    private Integer durationMin;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    private String imageUrl;
}
