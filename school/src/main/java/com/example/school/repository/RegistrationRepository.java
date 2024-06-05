package com.example.school.repository;

import com.example.school.entity.Registration;
import com.example.school.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
}
