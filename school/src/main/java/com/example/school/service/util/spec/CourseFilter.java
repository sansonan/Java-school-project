package com.example.school.service.util.spec;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CourseFilter {
    private Long id;
    private String title;
    private String instructor;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
