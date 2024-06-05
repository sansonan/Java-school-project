package com.example.school.service;

import com.example.school.entity.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    Payment makePayment(Long userId, BigDecimal amount, String paymentMethod);
    List<Payment> getPaymentHistory(Long userId);
}
