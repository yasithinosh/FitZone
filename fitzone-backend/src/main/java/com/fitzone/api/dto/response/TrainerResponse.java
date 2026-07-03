package com.fitzone.api.dto.response;

import lombok.Data;

@Data
public class TrainerResponse {
    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String bio;
    private String speciality;
    private Double rating;
    private String photoUrl;
}
