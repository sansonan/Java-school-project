package com.example.school.dto.dtos;

import lombok.Data;

@Data
public class FeeCalculationRequest {
    private Long courseId;
    private String promotionCode;
}
