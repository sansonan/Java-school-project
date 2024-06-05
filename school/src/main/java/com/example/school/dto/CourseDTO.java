package com.example.school.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private String instructor;
    private int duration;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String syllabus;
    private String status;
    private double ratings;
    private Long  categoryId;
}
