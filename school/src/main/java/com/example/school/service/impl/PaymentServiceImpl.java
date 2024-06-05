package com.example.school.service.impl;

import com.example.school.entity.Payment;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.PaymentRepository;
import com.example.school.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment makePayment(Long userId, BigDecimal amount, String paymentMethod) {
        // Validate inputs (optional)
        if (userId == null || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 || paymentMethod == null || paymentMethod.isEmpty()) {
            throw new ResourceNotFoundException("Invalid payment details provided");
        }

        // Create new payment
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentMethod);

        // Save payment to database
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentHistory(Long userId) {
        return null;
    }
}
