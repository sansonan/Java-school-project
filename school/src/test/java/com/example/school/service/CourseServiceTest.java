package com.example.school.service;

import com.example.school.entity.Course;
import com.example.school.entity.Permission;
import com.example.school.repository.CourseRepository;
import com.example.school.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCourse() {
        Course course = new Course();
        course.setTitle("Test Course");

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course createdCourse = courseService.createCourse(course);

        assertNotNull(createdCourse);
        assertEquals("Test Course", createdCourse.getTitle());

        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testGetCourseById() {
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);
        course.setTitle("Test Course");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        Course retrievedCourse = courseService.getCourseById(courseId);

        assertNotNull(retrievedCourse);
        assertEquals(courseId, retrievedCourse.getId());
        assertEquals("Test Course", retrievedCourse.getTitle());

        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void testGetAllCourses() {
        Course course1 = new Course();
        course1.setTitle("Course 1");

        Course course2 = new Course();
        course2.setTitle("Course 2");

        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> retrievedCourses = courseService.getAllCourses();

        assertNotNull(retrievedCourses);
        assertEquals(2, retrievedCourses.size());
        assertEquals("Course 1", retrievedCourses.get(0).getTitle());
        assertEquals("Course 2", retrievedCourses.get(1).getTitle());

        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testDeleteCourse(){
        Course course = new Course();
        course.setId(1L);
        course.setTitle("JAVA");

        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).delete(course);

        // Act
        courseService.deleteCourse(course.getId());

        // Assert
        verify(courseRepository, times(1)).delete(course);
    }

}
