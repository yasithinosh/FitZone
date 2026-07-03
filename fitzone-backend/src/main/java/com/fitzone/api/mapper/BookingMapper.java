package com.fitzone.api.mapper;

import com.fitzone.api.dto.response.BookingResponse;
import com.fitzone.api.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "gymClass.id", target = "classId")
    @Mapping(source = "gymClass.name", target = "className")
    @Mapping(source = "gymClass.trainer.user.name", target = "trainerName")
    @Mapping(source = "gymClass.startTime", target = "startTime")
    @Mapping(source = "gymClass.durationMin", target = "durationMin")
    @Mapping(source = "status", target = "status")
    BookingResponse toResponse(Booking booking);
}
