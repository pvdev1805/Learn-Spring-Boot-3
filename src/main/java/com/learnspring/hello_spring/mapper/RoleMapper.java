package com.learnspring.hello_spring.mapper;

import com.learnspring.hello_spring.dto.request.RoleRequest;
import com.learnspring.hello_spring.dto.response.RoleResponse;
import com.learnspring.hello_spring.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
