package com.learnspring.hello_spring.mapper;

import com.learnspring.hello_spring.dto.request.PermissionRequest;
import com.learnspring.hello_spring.dto.response.PermissionResponse;
import com.learnspring.hello_spring.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
