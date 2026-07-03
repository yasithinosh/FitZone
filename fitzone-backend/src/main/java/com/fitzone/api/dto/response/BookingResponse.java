package com.fitzone.api.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingResponse {
    private Long id;
    private Long memberId;
    private Long classId;
    private String className;
    private String trainerName;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private Integer durationMin;
    private String status;
}
