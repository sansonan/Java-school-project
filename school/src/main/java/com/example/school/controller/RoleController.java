package com.example.school.controller;


import com.example.school.dto.RoleDTO;
import com.example.school.entity.Role;
import com.example.school.service.RoleService;
import com.example.school.service.util.RoleMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> list = roleService.getAllRoles()
                .stream()
                .map(role -> RoleMapper.INSTANCE.toRoleDto(role) )
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        Role roleById = roleService.getRoleById(id);
        return ResponseEntity.ok(RoleMapper.INSTANCE.toRoleDto(roleById));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.toRole(roleDTO);
        role = roleService.createRole(role);
        return ResponseEntity.ok(RoleMapper.INSTANCE.toRoleDto(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleUpdateDTO) {
        Role role = RoleMapper.INSTANCE.toRole(roleUpdateDTO);
        Role roleUpdate = roleService.updateRole(id, role);
        return ResponseEntity.ok(RoleMapper.INSTANCE.toRoleDto(roleUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}

