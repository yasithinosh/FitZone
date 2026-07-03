package com.fitzone.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "gym_classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassIntensity intensity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "duration_min", nullable = false)
    private Integer durationMin;

    @Column(nullable = false)
    private Integer capacity;

    @Builder.Default
    @Column(name = "current_count")
    private Integer currentCount = 0;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;
}
