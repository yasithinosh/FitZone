package com.fitzone.api.mapper;

import com.fitzone.api.dto.response.ClassResponse;
import com.fitzone.api.model.GymClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(source = "trainer.id", target = "trainerId")
    @Mapping(source = "trainer.user.name", target = "trainerName")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "intensity", target = "intensity")
    ClassResponse toResponse(GymClass gymClass);
}
