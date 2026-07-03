package com.fitzone.api.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DashboardResponse {
    private String memberName;
    private String membershipType;
    private LocalDate expiryDate;
    
    private int totalBookings;
    private int classesThisMonth;
    private int currentStreak;
    private int daysLeft;
    
    private List<BookingResponse> upcomingBookings;
}
