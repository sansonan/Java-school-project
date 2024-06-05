package com.example.school.service;

import com.example.school.entity.User;
import com.example.school.repository.UserRepository;
import com.example.school.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

   @Mock
    private  PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("sokdara");
        user.setFullName("sok dara");
        user.setEmail("sokdara@example.com");
        user.setPassword(passwordEncoder.encode("password"));

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User createdUser = userService.createUser(user);

        // Assert
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getUsername(), createdUser.getUsername());
        assertEquals(user.getFullName(), createdUser.getFullName());
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    void testGetUserById() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("sokdara");
        user.setFullName("sok dara");
        user.setEmail("sokdara@example.com");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.getUserById(user.getId());

        // Assert
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getFullName(), foundUser.getFullName());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("sokdara");
        user1.setFullName("sok dara");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("chantha");
        user2.setFullName("chan tha");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(2, result.size());
        assertEquals(user1.getUsername(), result.get(0).getUsername());
        assertEquals(user2.getUsername(), result.get(1).getUsername());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("sokdara");
        existingUser.setFullName("sok dara");
        existingUser.setEmail("sokdara@example.com");
        existingUser.setPassword("password");

        User updatedUser = new User();
        updatedUser.setUsername("chantha");
        updatedUser.setFullName("chan tha");
        updatedUser.setEmail("chantha@example.com");
        updatedUser.setPassword(passwordEncoder.encode("newpassword"));

        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        User result = userService.updateUser(existingUser.getId(), updatedUser);

        // Assert
        assertEquals(existingUser.getId(), result.getId());
        assertEquals(updatedUser.getUsername(), result.getUsername());
        assertEquals(updatedUser.getFullName(), result.getFullName());
        assertEquals(updatedUser.getEmail(), result.getEmail());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("sokdara");
        user.setFullName("sok dara");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        // Act
        userService.deleteUser(user.getId());

        // Assert
        verify(userRepository, times(1)).delete(user);
    }
}
