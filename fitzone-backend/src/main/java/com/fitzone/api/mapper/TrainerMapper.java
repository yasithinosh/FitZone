package com.fitzone.api.mapper;

import com.fitzone.api.dto.response.TrainerResponse;
import com.fitzone.api.model.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")
    TrainerResponse toResponse(Trainer trainer);
}
