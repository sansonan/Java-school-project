package com.example.school.service.util;

import com.example.school.dto.CourseDTO;
import com.example.school.entity.Course;
import com.example.school.service.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( componentModel = "spring", uses = {CategoryService.class})
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "category", source = "categoryId")
    Course toCourse(CourseDTO courseDTO);


    @Mapping(target = "categoryId", source = "category.id")
    CourseDTO toCourseDto(Course course);

}
