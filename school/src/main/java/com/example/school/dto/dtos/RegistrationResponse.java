package com.example.school.dto.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class RegistrationResponse {
    private Long registrationId;
    private Long userId;
    private List<Long> courseIds;
    private BigDecimal totalFee; // Optional, if you want to include the total fee
    private String promotionCode; // Optional, if a promotion was applied
    private String status;
}
