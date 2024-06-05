package com.example.school.repository;

import com.example.school.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> ,
        JpaSpecificationExecutor<Course> {
//    List<Course> findByCategoryById(Long categoryId);


}
