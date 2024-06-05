package com.example.school.service;

import com.example.school.dto.dtos.FeeCalculationRequest;
import com.example.school.dto.dtos.RegistrationRequest;
import com.example.school.dto.dtos.RegistrationResponse;
import com.example.school.entity.Course;
import com.example.school.entity.Registration;
import com.example.school.entity.User;

import java.math.BigDecimal;
import java.util.List;

//public interface RegistrationService {
//    Registration registerCourses(User user, List<Course> courses);
//    BigDecimal calculateRegistrationFee(Course course, String promotionCode);
//    Registration registerForCourse(User user, Course course, String promotionCode);
//}

public interface RegistrationService {

    /**
     * Registers a user for a list of courses using a DTO.
     * @param registrationRequest the registration request DTO containing user ID and course IDs
     * @return the registration details
     */
    RegistrationResponse registerCourses(RegistrationRequest registrationRequest);

    /**
     * Calculates the registration fee for a course using a DTO.
//     * @param feeCalculationRequest the fee calculation request DTO containing course ID and promotion code
     * @return the calculated registration fee
     */
    BigDecimal calculateRegistrationFee(Long courseId, String promotionCode);

    /**
     * Registers a user for a single course using a DTO.
     * @param registrationRequest the registration request DTO containing user ID, course ID, and promotion code
     * @return the registration details
     */
//    RegistrationResponse registerForCourse(RegistrationRequest registrationRequest);


}

