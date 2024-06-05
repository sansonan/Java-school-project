package com.example.school.dto.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequest {
    private Long userId;
//    private String userType;
    private List<Long> courseIds;
    private String promotionCode;
}
