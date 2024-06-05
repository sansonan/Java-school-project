package com.example.school.service;

import com.example.school.dto.RoleDTO;
import com.example.school.entity.Role;

import java.util.List;

public interface RoleService {
//    List<RoleDTO> getAllRoles();
//    RoleDTO getRoleById(Long id);
//    RoleDTO createRole(RoleDTO roleDTO);
//    RoleDTO updateRole(Long id, RoleDTO roleDTO);
//    void deleteRole(Long id);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role createRole(Role role);
    Role updateRole(long id, Role role);
    void deleteRole(Long id);
}
