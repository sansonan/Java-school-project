package com.example.school.controller;

import com.example.school.entity.Payment;
import com.example.school.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<Payment> makePayment(@RequestParam Long userId,
                                               @RequestParam BigDecimal amount,
                                               @RequestParam String paymentMethod) {
        Payment payment = paymentService.makePayment(userId, amount, paymentMethod);
        return ResponseEntity.ok(payment);
    }
}