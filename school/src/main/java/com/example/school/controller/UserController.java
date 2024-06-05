package com.example.school.controller;


import com.example.school.dto.UserDTO;
import com.example.school.entity.User;
import com.example.school.service.UserService;
import com.example.school.service.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @PreAuthorize("hasAnyAuthority('user:write')")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toUser(userDTO);
        user = userService.createUser(user);
        return  ResponseEntity.ok(UserMapper.INSTANCE.toUserDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDto(user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(user -> UserMapper.INSTANCE.toUserDto(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDto) {
        User user = UserMapper.INSTANCE.toUser(updatedUserDto);
        User userUpdate = userService.updateUser(id, user);
        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDto(userUpdate));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
