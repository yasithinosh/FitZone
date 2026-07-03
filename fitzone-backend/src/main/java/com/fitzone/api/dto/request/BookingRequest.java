package com.fitzone.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    @NotNull(message = "Class ID is required")
    private Long classId;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;
}
