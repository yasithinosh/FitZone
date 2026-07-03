package com.fitzone.api.service;

import com.fitzone.api.dto.request.BookingRequest;
import com.fitzone.api.dto.response.BookingResponse;
import com.fitzone.api.exception.ResourceNotFoundException;
import com.fitzone.api.mapper.BookingMapper;
import com.fitzone.api.model.Booking;
import com.fitzone.api.model.BookingStatus;
import com.fitzone.api.model.GymClass;
import com.fitzone.api.model.Member;
import com.fitzone.api.repository.BookingRepository;
import com.fitzone.api.repository.GymClassRepository;
import com.fitzone.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MemberRepository memberRepository;
    private final GymClassRepository gymClassRepository;
    private final BookingMapper bookingMapper;

    public List<BookingResponse> getMyBookings(Long memberId) {
        // Here we'd ideally fetch bookings specifically for the memberId.
        // Assuming BookingRepository has findByMemberId, but we'll use findAll and filter for now.
        return bookingRepository.findAll().stream()
                .filter(b -> b.getMember().getId().equals(memberId))
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResponse createBooking(Long memberId, BookingRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        GymClass gymClass = gymClassRepository.findById(request.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));

        if (gymClass.getCurrentCount() >= gymClass.getCapacity()) {
            throw new RuntimeException("Class is full");
        }

        // Ideally check for duplicate booking here too

        Booking booking = Booking.builder()
                .member(member)
                .gymClass(gymClass)
                .bookingDate(request.getBookingDate())
                .status(BookingStatus.CONFIRMED)
                .build();

        gymClass.setCurrentCount(gymClass.getCurrentCount() + 1);
        gymClassRepository.save(gymClass);

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponse cancelBooking(Long bookingId, Long memberId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (!booking.getMember().getId().equals(memberId)) {
            throw new RuntimeException("Unauthorized to cancel this booking");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        
        GymClass gymClass = booking.getGymClass();
        gymClass.setCurrentCount(Math.max(0, gymClass.getCurrentCount() - 1));
        gymClassRepository.save(gymClass);

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponse updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }
}
