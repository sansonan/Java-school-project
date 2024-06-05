package com.example.school.service;


import com.example.school.entity.User;
import com.example.school.security.AuthUser;


import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<AuthUser> findUserByUsername(String username);
    User createUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
    User getUserByUsername(String username);


}
