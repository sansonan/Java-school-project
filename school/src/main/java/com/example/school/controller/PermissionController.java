package com.example.school.controller;

import com.example.school.dto.PermissionDTO;
import com.example.school.entity.Permission;
import com.example.school.service.PermissionService;
import com.example.school.service.util.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping
    public ResponseEntity<PermissionDTO> create(@RequestBody PermissionDTO dto){

        Permission permission = PermissionMapper.INSTANCE.toPermission(dto);
        permission = permissionService.createPermission(permission);

        return ResponseEntity.ok(PermissionMapper.INSTANCE.toPermissionDto(permission));
    }


    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAllPermission(){
        List<PermissionDTO> list = permissionService.getAllPermissions()
                .stream()
                .map(permission -> PermissionMapper.INSTANCE.toPermissionDto(permission))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable Long id){
        Permission permissionId = permissionService.getPermissionById(id);
        return ResponseEntity.ok(PermissionMapper.INSTANCE.toPermissionDto(permissionId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDTO> updatePermission(@PathVariable Long id, PermissionDTO update){
        Permission permission = PermissionMapper.INSTANCE.toPermission(update);
        Permission updatePermission = permissionService.updatePermission(id, permission);
        return ResponseEntity.ok(PermissionMapper.INSTANCE.toPermissionDto(updatePermission));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id){
        String deletePermission = permissionService.deletePermission(id);
        return ResponseEntity.ok(deletePermission);
    }

}
