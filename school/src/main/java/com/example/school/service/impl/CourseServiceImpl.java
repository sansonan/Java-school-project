package com.example.school.service.impl;

import com.example.school.entity.Course;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.CourseRepository;
import com.example.school.service.CourseService;
import com.example.school.service.util.spec.CourseFilter;
import com.example.school.service.util.spec.CourseSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }


    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByIds(List<Long> ids) {
        return courseRepository.findAllById(ids);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = getCourseById(id);
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        course.setInstructor(updatedCourse.getInstructor());
        course.setDuration(updatedCourse.getDuration());
        course.setPrice(updatedCourse.getPrice());
        course.setCategory(updatedCourse.getCategory());
        course.setStartDate(updatedCourse.getStartDate());
        course.setEndDate(updatedCourse.getEndDate());
        course.setSyllabus(updatedCourse.getSyllabus());
        course.setStatus(updatedCourse.getStatus());
        course.setRatings(updatedCourse.getRatings());
        return courseRepository.save(course);
    }

    @Override
    public String deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
        return "Successfully deleted!";
    }

    @Override
    public List<Course> getCourse(Map<String, String> params) {
        CourseFilter courseFilter = new CourseFilter();
        if(params.containsKey("title")){
            String title = params.get("title");
            courseFilter.setTitle(title);
        }
        if(params.containsKey("instructor")){
            String instructor = params.get("instructor");
            courseFilter.setInstructor(instructor);
        }

        CourseSpec courseSpec = new CourseSpec(courseFilter);
        return courseRepository.findAll(courseSpec);
    }
}
