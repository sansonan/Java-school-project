package com.example.school.service.impl;

import com.example.school.dto.dtos.FeeCalculationRequest;
import com.example.school.dto.dtos.RegistrationRequest;
import com.example.school.dto.dtos.RegistrationResponse;
import com.example.school.entity.Course;
import com.example.school.entity.Registration;
import com.example.school.entity.User;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.RegistrationRepository;
import com.example.school.repository.UserRepository;
import com.example.school.service.PromotionService;
import com.example.school.service.RegistrationService;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PromotionService promotionService; // Assuming you have a service for handling promotions

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public RegistrationResponse registerCourses(RegistrationRequest registrationRequest) {
        // Validate user
        User user = userRepository.findById(registrationRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Validate courses
        List<Course> courses = courseRepository.findAllById(registrationRequest.getCourseIds());
        if (courses.isEmpty() || courses.size() != registrationRequest.getCourseIds().size()) {
            throw new ResourceNotFoundException("One or more courses not found");
        }

        // Create a new registration
        Registration registration = new Registration();
        registration.setUser(user);
        registration.setCourses(courses);

        // Apply promotion if present
        BigDecimal totalFee = BigDecimal.ZERO;
        if (registrationRequest.getPromotionCode() != null) {
            for (Course course : courses) {
                BigDecimal discount = promotionService.getDiscount(registrationRequest.getPromotionCode(), course);
                totalFee = totalFee.add(course.getPrice().subtract(discount));
            }
            registration.setPromotions(
                    promotionService.applyPromotion(registrationRequest.getPromotionCode(), user.getId())
            );
        } else {
            totalFee = courses.stream()
                    .map(Course::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        // Save the registration
        registration = registrationRepository.save(registration);

        // Prepare the response
        RegistrationResponse response = new RegistrationResponse();
        response.setRegistrationId(registration.getId());
        response.setUserId(user.getId());
        response.setCourseIds(courses.stream().map(Course::getId).collect(Collectors.toList()));
        response.setTotalFee(totalFee);
        response.setPromotionCode(registrationRequest.getPromotionCode());
        response.setStatus("Registered");
        return response;
    }


    @Override
    public BigDecimal calculateRegistrationFee(Long courseId, String promotionCode) {
        //Fetch the course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Calculate the fee with promotion
        BigDecimal discount = promotionService.getDiscount(promotionCode, course);
        return course.getPrice().subtract(discount);
    }


//    @Override
//    public RegistrationResponse registerForCourse(RegistrationRequest registrationRequest) {
//        // Validate input
//        if (registrationRequest.getCourseIds().size() != 1) {
//            throw new IllegalArgumentException("Only one course ID should be provided for single course registration");
//        }
//        Long courseId = registrationRequest.getCourseIds().get(0);
//
//        // Fetch the user and course
//        User user = userRepository.findById(registrationRequest.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        Long userId = registrationRequest.getUserId();
//
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
//
//        // Calculate the fee with promotion
//        BigDecimal totalFee = calculateRegistrationFee(courseId, registrationRequest.getPromotionCode());
//
//
//        // Create the registration
//        Registration registration = new Registration();
//        registration.setUser(user);
//        registration.setCourses(List.of(course));
//        registration.setPromotions(promotionService.applyPromotions(registrationRequest.getPromotionCode()));
//        registration = registrationRepository.save(registration);
//
//        // Prepare the response
//        RegistrationResponse response = new RegistrationResponse();
//        response.setRegistrationId(registration.getId());
//        response.setTotalFee(totalFee);
//        response.setStatus("Registered");
//
//        return response;
//    }


}
