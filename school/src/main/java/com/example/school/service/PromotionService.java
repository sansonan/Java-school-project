package com.example.school.service;

import com.example.school.entity.Course;
import com.example.school.entity.Promotion;
import com.example.school.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionService {
    Promotion createPromotion(Promotion promotion);
    Promotion getPromotionById(Long id);
    List<Promotion> getAllPromotions();

    Promotion updatePromotion(Long id, Promotion updatedPromotion);
    String deletePromotion(Long id);
    List<Promotion> getActivePromotions();

    public BigDecimal getDiscount(String promotionCode, Course course) ;
    List<Promotion> applyPromotion(String promotionCode, Long userId);

    BigDecimal getDiscountStudentRegisteredAtLeastThreeCourse(String promotionCode, Long courseId);

}
