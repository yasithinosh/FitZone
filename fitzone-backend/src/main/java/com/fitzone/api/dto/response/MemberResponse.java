package com.fitzone.api.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MemberResponse {
    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String membershipType;
    private LocalDate joinDate;
    private LocalDate expiryDate;
    private String status;
}
