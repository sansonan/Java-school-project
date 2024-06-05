package com.example.school.service.util;

import com.example.school.dto.PermissionDTO;
import com.example.school.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
    PermissionDTO toPermissionDto (Permission permission);
    Permission toPermission(PermissionDTO permissionDTO);
}
