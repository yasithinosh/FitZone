package com.fitzone.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trainers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(length = 100)
    private String speciality;

    @Builder.Default
    @Column(precision = 2, scale = 1)
    private Double rating = 5.0;

    @Column(name = "photo_url", length = 500)
    private String photoUrl;
}
