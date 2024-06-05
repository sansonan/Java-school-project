package com.example.school.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.school.entity.Permission;
import com.example.school.repository.PermissionRepository;
import com.example.school.service.impl.PermissionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePermission() {
        // Arrange
        Permission permission = new Permission();
        permission.setName("PERMISSION_READ");

        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);

        // Act
        Permission createdPermission = permissionService.createPermission(permission);

        // Assert
        assertEquals("PERMISSION_READ", createdPermission.getName());
    }

    @Test
    void testGetPermissionById() {
        // Arrange
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setName("PERMISSION_READ");

        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));

        // Act
        Permission foundPermission = permissionService.getPermissionById(1L);

        // Assert
        assertEquals(1L, foundPermission.getId());
        assertEquals("PERMISSION_READ", foundPermission.getName());
    }

    @Test
    void testGetAllPermissions() {
        // Arrange
        Permission permission1 = new Permission();
        permission1.setId(1L);
        permission1.setName("PERMISSION_READ");

        Permission permission2 = new Permission();
        permission2.setId(2L);
        permission2.setName("PERMISSION_WRITE");

        List<Permission> permissions = Arrays.asList(permission1, permission2);

        when(permissionRepository.findAll()).thenReturn(permissions);

        // Act
        List<Permission> allPermissions = permissionService.getAllPermissions();

        // Assert
        assertEquals(2, allPermissions.size());
        assertEquals("PERMISSION_READ", allPermissions.get(0).getName());
        assertEquals("PERMISSION_WRITE", allPermissions.get(1).getName());
    }

    @Test
    void testUpdatePermission() {
        // Arrange
        Permission existingPermission = new Permission();
        existingPermission.setId(1L);
        existingPermission.setName("PERMISSION_READ");

        Permission updatedPermissionData = new Permission();
        updatedPermissionData.setName("PERMISSION_UPDATE");

        when(permissionRepository.findById(1L)).thenReturn(Optional.of(existingPermission));
        when(permissionRepository.save(any(Permission.class))).thenReturn(updatedPermissionData);

        // Act
        Permission updatedPermission = permissionService.updatePermission(1L, updatedPermissionData);

        // Assert
        assertEquals("PERMISSION_UPDATE", updatedPermission.getName());
    }

    @Test
    void testDeletePermission(){
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setName("PERMISSION_READ");

        when(permissionRepository.findById(permission.getId())).thenReturn(Optional.of(permission));
        doNothing().when(permissionRepository).delete(permission);

        // Act
        permissionService.deletePermission(permission.getId());

        // Assert
        verify(permissionRepository, times(1)).delete(permission);
    }

}
