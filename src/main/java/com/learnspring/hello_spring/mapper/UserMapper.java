package com.learnspring.hello_spring.mapper;

import com.learnspring.hello_spring.dto.request.UserCreationRequest;
import com.learnspring.hello_spring.dto.request.UserUpdateRequest;
import com.learnspring.hello_spring.dto.response.UserResponse;
import com.learnspring.hello_spring.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
