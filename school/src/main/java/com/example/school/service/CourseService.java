package com.example.school.service;

import com.example.school.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Course createCourse(Course course);
    Course getCourseById(Long id);
//    List<Course> getCategoryById(Long categoryId);
    List<Course> getAllCourses();
    List<Course> getCoursesByIds(List<Long> ids);
    Course updateCourse(Long id, Course updatedCourse);
    String deleteCourse(Long id);
    List<Course> getCourse(Map<String,String> params);
}
