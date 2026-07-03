package com.fitzone.api.controller;

import com.fitzone.api.dto.request.BookingRequest;
import com.fitzone.api.dto.response.ApiResponse;
import com.fitzone.api.dto.response.BookingResponse;
import com.fitzone.api.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getMyBookings() {
        // Ideally extract memberId from SecurityContext
        Long memberId = 1L; // Stubbed for now
        return ResponseEntity.ok(ApiResponse.success(bookingService.getMyBookings(memberId), "Bookings retrieved successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@Valid @RequestBody BookingRequest request) {
        Long memberId = 1L; // Stubbed for now
        return ResponseEntity.ok(ApiResponse.success(bookingService.createBooking(memberId, request), "Booking created successfully"));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(@PathVariable Long id) {
        Long memberId = 1L; // Stubbed for now
        return ResponseEntity.ok(ApiResponse.success(bookingService.cancelBooking(id, memberId), "Booking cancelled successfully"));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BookingResponse>> updateBookingStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        return ResponseEntity.ok(ApiResponse.success(bookingService.updateBookingStatus(id, statusUpdate.get("status")), "Booking status updated successfully"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings() {
        return ResponseEntity.ok(ApiResponse.success(bookingService.getAllBookings(), "All bookings retrieved successfully"));
    }
}
