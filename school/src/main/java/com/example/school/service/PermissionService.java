package com.example.school.service;

import com.example.school.entity.Permission;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PermissionService {
    Permission createPermission (Permission permission);
    Permission getPermissionById(Long id);
    List<Permission> getAllPermissions();
    Permission updatePermission(Long id, Permission updatePermission);
    String deletePermission(Long id);
}
