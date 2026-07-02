package com.ramesh.studenterp.mapper;

import com.ramesh.studenterp.dto.request.RegisterRequest;
import com.ramesh.studenterp.dto.response.UserResponse;
import com.ramesh.studenterp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    @Mapping(target = "role", source = "role.name")
    UserResponse toResponse(User user);
}
