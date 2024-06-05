package com.example.school.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.school.entity.Role;
import com.example.school.repository.RoleRepository;
import com.example.school.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRole() {
        // Arrange
        Role role = new Role();
        role.setName("ROLE_ADMIN");

        when(roleRepository.save(any(Role.class))).thenReturn(role);

        // Act
        Role createdRole = roleService.createRole(role);

        // Assert
        assertEquals("ROLE_ADMIN", createdRole.getName());
    }

    @Test
    void testGetRoleById() {
        // Arrange
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        // Act
        Role foundRole = roleService.getRoleById(1L);

        // Assert
        assertEquals(1L, foundRole.getId());
        assertEquals("ROLE_ADMIN", foundRole.getName());
    }


    @Test
    void testGetAllRoles() {
        // Arrange
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_ADMIN");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("ROLE_USER");

        List<Role> roles = Arrays.asList(role1, role2);

        when(roleRepository.findAll()).thenReturn(roles);

        // Act
        List<Role> allRoles = roleService.getAllRoles();

        // Assert
        assertEquals(2, allRoles.size());
        assertEquals("ROLE_ADMIN", allRoles.get(0).getName());
        assertEquals("ROLE_USER", allRoles.get(1).getName());
    }

    @Test
    void testUpdateRole() {
        // Arrange
        Role existingRole = new Role();
        existingRole.setId(1L);
        existingRole.setName("ROLE_ADMIN");

        Role updatedRoleData = new Role();
        updatedRoleData.setName("ROLE_SUPER_ADMIN");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
        when(roleRepository.save(any(Role.class))).thenReturn(updatedRoleData);

        // Act
        Role updatedRole = roleService.updateRole(1L, updatedRoleData);

        // Assert
        assertEquals("ROLE_SUPER_ADMIN", updatedRole.getName());
    }

}
