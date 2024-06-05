package com.example.school.service;

import com.example.school.entity.Payment;
import com.example.school.repository.PaymentRepository;
import com.example.school.service.PaymentService;
import com.example.school.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakePayment() {
        // Arrange
        Long userId = 1L;
        BigDecimal amount = new BigDecimal("100.00");
        String paymentMethod = "Credit Card";

        Payment payment = new Payment();
        payment.setId(1L);
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentMethod);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // Act
        Payment result = paymentService.makePayment(userId, amount, paymentMethod);

        // Assert
        assertEquals(userId, result.getUserId());
        assertEquals(amount, result.getAmount());
        assertEquals(paymentMethod, result.getPaymentMethod());
    }
}
