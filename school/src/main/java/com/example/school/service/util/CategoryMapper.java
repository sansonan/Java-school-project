package com.example.school.service.util;

import com.example.school.dto.CategoryDTO;
import com.example.school.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toCategory(CategoryDTO categoryDTO);
    CategoryDTO toCategoryDTO (Category entity);

}