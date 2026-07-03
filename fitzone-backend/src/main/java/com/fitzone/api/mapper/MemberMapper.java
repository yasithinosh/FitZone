package com.fitzone.api.mapper;

import com.fitzone.api.dto.response.MemberResponse;
import com.fitzone.api.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "membership.type", target = "membershipType")
    MemberResponse toResponse(Member member);
}
