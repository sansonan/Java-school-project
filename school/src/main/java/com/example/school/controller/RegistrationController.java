package com.example.school.controller;

import com.example.school.dto.dtos.RegistrationRequest;
import com.example.school.dto.dtos.RegistrationResponse;
import com.example.school.entity.Course;
import com.example.school.entity.Registration;
import com.example.school.entity.User;
import com.example.school.service.CourseService;
import com.example.school.service.RegistrationService;
import com.example.school.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final CourseService courseService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerCourses(@RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse response = registrationService.registerCourses(registrationRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/calculate-fee")
    public ResponseEntity<?> calculateRegistrationFee(@RequestParam Long courseId, @RequestParam(required = false) String promotionCode){
        BigDecimal fee = registrationService.calculateRegistrationFee(courseId, promotionCode);
        return ResponseEntity.ok(fee);
    }
//
//    @PostMapping("/forcourse")
//    public ResponseEntity<?> registerForCourse(@RequestBody  RegistrationRequest registrationRequest){
//        RegistrationResponse response = registrationService.registerForCourse(registrationRequest);
//        return ResponseEntity.ok(response);
//    }


}
