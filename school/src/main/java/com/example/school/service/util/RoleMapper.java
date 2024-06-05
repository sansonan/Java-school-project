package com.example.school.service.util;

import com.example.school.dto.RoleDTO;
import com.example.school.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toRoleDto (Role role);
    Role toRole(RoleDTO roleDTO);

//    public static RoleDTO toRoleDTO(Role role) {
//        RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId(role.getId());
//        roleDTO.setName(role.getName());
//        // Map other fields as needed
//        return roleDTO;
//    }
//
//    public static Role toRole(RoleDTO roleDTO) {
//        Role role = new Role();
//        role.setId(roleDTO.getId());
//        role.setName(roleDTO.getName());
//        // Map other fields as needed
//        return role;
//    }

}
