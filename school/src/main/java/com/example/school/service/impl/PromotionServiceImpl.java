package com.example.school.service.impl;

import com.example.school.entity.Course;
import com.example.school.entity.Promotion;
import com.example.school.entity.Registration;
import com.example.school.entity.User;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.PromotionRepository;
import com.example.school.repository.RegistrationRepository;
import com.example.school.repository.UserRepository;
import com.example.school.service.PromotionService;
import com.example.school.service.RegistrationService;
import com.example.school.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private final PromotionRepository promotionRepository;

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion", id));
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion updatePromotion(Long id, Promotion updatedPromotion) {
        Promotion promotion = getPromotionById(id);
        promotion.setTitle(updatedPromotion.getTitle());
        promotion.setDescription(updatedPromotion.getDescription());
        promotion.setDiscountPercentage(updatedPromotion.getDiscountPercentage());
        promotion.setStartDate(updatedPromotion.getStartDate());
        promotion.setEndDate(updatedPromotion.getEndDate());
        promotion.setApplicableCourses(updatedPromotion.getApplicableCourses());
        promotion.setStatus(updatedPromotion.getStatus());
        promotion.setUsageLimit(updatedPromotion.getUsageLimit());
        // Update other properties as needed
        return promotionRepository.save(promotion);
    }

    @Override
    public String deletePromotion(Long id) {
        Promotion promotion = getPromotionById(id);
        promotionRepository.delete(promotion);
        return "Successfully deleted!";
    }

    @Override
    public List<Promotion> getActivePromotions() {
        return promotionRepository.findByStatus("active");
    }

//    @Override
//    public List<Promotion> getActivePromotions() {
//        return promotionRepository.findAll().stream()
//                .filter(promotion -> "active".equalsIgnoreCase(promotion.getStatus()) &&
//                        (promotion.getStartDate().isBefore(LocalDate.now()) || promotion.getStartDate().isEqual(LocalDate.now())) &&
//                        (promotion.getEndDate().isAfter(LocalDate.now()) || promotion.getEndDate().isEqual(LocalDate.now())))
//                .collect(Collectors.toList());
//    }


    @Override
    public BigDecimal getDiscount(String promotionCode, Course course) {
        Optional<Promotion> promotionOptional = promotionRepository.findByTitle(promotionCode);
        if (promotionOptional.isPresent()) {
            Promotion promotion = promotionOptional.get();
            if ("active".equalsIgnoreCase(promotion.getStatus()) &&
                    (promotion.getStartDate().isBefore(LocalDate.now()) || promotion.getStartDate().isEqual(LocalDate.now())) &&
                    (promotion.getEndDate().isAfter(LocalDate.now()) || promotion.getEndDate().isEqual(LocalDate.now()))) {
                // Apply discount logic
                return course.getPrice().multiply(promotion.getDiscountPercentage().divide(BigDecimal.valueOf(100)));
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public List<Promotion> applyPromotion(String promotionCode, Long userId) {
        // Fetch the promotion by its code
        Promotion promotion = promotionRepository.findByTitle(promotionCode)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion code not found"));

        // Validate promotion status and dates
        if (!"active".equalsIgnoreCase(promotion.getStatus()) ||
                promotion.getStartDate().isAfter(LocalDate.now()) ||
                promotion.getEndDate().isBefore(LocalDate.now())) {
            throw new ResourceNotFoundException("Promotion is not active or not valid for the current date");
        }

        // Fetch the user by their ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Fetch all registrations for the user
        List<Registration> registrations = registrationRepository.findByUser(user);

        // Apply the promotion to each registration
        for (Registration registration : registrations) {
            if (!registration.getPromotions().contains(promotion)) {
                registration.getPromotions().add(promotion);
                registrationRepository.save(registration);
            }
        }

        // Collect all unique promotions from the user's registrations
        return registrations.stream()
                .flatMap(registration -> registration.getPromotions().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getDiscountStudentRegisteredAtLeastThreeCourse(String promotionCode, Long courseId) {
        // Retrieve the promotion based on the promotion code
        Promotion promotion = promotionRepository.findByTitle(promotionCode)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion code not found"));

        // Validate the promotion status and dates
        if (!"active".equalsIgnoreCase(promotion.getStatus()) ||
                promotion.getStartDate().isAfter(LocalDate.now()) ||
                promotion.getEndDate().isBefore(LocalDate.now())) {
            throw new ResourceNotFoundException("Promotion is not active or not valid for the current date");
        }
        // Calculate the discount for the specified course
        return promotion.getDiscountPercentage().multiply(BigDecimal.valueOf(0.5));

    }

}
