package com.example.school.service.impl;

import com.example.school.entity.Permission;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.PermissionRepository;
import com.example.school.service.PermissionService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public Permission createPermission(Permission permission) {
        if (permission.getId() != null) {
            throw new IllegalArgumentException("ID must be null for a new permission");
        }
        return permissionRepository.save(permission);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Permission ",id));
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission updatePermission(Long id, Permission updatePermission) {
        Permission permission = getPermissionById(id);
        permission.setId(updatePermission.getId());
        permission.setName(updatePermission.getName());
        return permissionRepository.save(permission);
    }

    @RolesAllowed("ROLE_ADMIN")
    @Override
    public String deletePermission(Long id) {
        Permission permission = getPermissionById(id);
        permissionRepository.delete(permission);
        return "Successfully deleted!";
    }
}
