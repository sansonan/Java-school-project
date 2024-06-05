package com.example.school.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class PromotionDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String applicableCourses;
    private String status;
    private Integer usageLimit;

}
