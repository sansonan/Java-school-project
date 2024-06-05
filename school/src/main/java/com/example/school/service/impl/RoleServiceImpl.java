package com.example.school.service.impl;

import com.example.school.entity.Role;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.RoleRepository;
import com.example.school.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(long id, Role roleUpdate) {
        Role role = getRoleById(id);
        role.setName(role.getName());
        role.setPermissions(role.getPermissions());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        roleRepository.delete(role);
    }

//    @Override
//    public List<RoleDTO> getAllRoles() {
//     return roleRepository.findAll().stream().map(RoleMapper::toRoleDTO).collect(Collectors.toList());
//    }
//
//    @Override
//    public RoleDTO getRoleById(Long id) {
//        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
//        return RoleMapper.toRoleDTO(role);
//    }
//
//    @Override
//    public RoleDTO createRole(RoleDTO roleDTO) {
//        Role role = RoleMapper.toRole(roleDTO);
//        role = roleRepository.save(role);
//        return RoleMapper.toRoleDTO(role);
//    }
//
//    @Override
//    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
//        Role existingRole = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
//        existingRole.setName(roleDTO.getName());
//        // Update other fields as needed
//        existingRole = roleRepository.save(existingRole);
//        return RoleMapper.toRoleDTO(existingRole);
//    }
//
//    @Override
//    public void deleteRole(Long id) {
//        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
//        roleRepository.delete(role);
//    }
}
