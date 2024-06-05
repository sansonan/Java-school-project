package com.example.school.controller;

import com.example.school.dto.CourseDTO;
import com.example.school.entity.Course;
import com.example.school.service.CourseService;
import com.example.school.service.util.CourseMapper;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        Course course = courseMapper.toCourse(courseDTO);
        course = courseService.createCourse(course);
        return ResponseEntity.ok(courseMapper.toCourseDto(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(courseMapper.toCourseDto(course));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> list = courseService.getAllCourses()
                .stream()
                .map(course -> courseMapper.toCourseDto(course) )
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO updateCourseDto) {
       Course course = courseMapper.toCourse(updateCourseDto);
       Course courseUpdate = courseService.updateCourse(id, course);
        return ResponseEntity.ok(courseMapper.toCourseDto(courseUpdate));
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        String deleteCourse = courseService.deleteCourse(id);
        return ResponseEntity.ok(deleteCourse);
    }

}
