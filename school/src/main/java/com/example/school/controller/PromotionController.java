package com.example.school.controller;

import com.example.school.dto.CourseDTO;
import com.example.school.dto.PromotionDTO;
import com.example.school.entity.Course;
import com.example.school.entity.Promotion;
import com.example.school.entity.User;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.service.CourseService;
import com.example.school.service.PromotionService;
import com.example.school.service.UserService;
import com.example.school.service.util.CourseMapper;
import com.example.school.service.util.PromotionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;
    private final CourseService courseService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO){
        Promotion promotion = PromotionMapper.INSTANCE.toPromotion(promotionDTO);
        promotion = promotionService.createPromotion(promotion);
        return ResponseEntity.ok(PromotionMapper.INSTANCE.toPromotionDto(promotion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> getPromotionById(@PathVariable Long id){
        Promotion promotion = promotionService.getPromotionById(id);
        return ResponseEntity.ok(PromotionMapper.INSTANCE.toPromotionDto(promotion));
    }

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> getAllPromotions(){
        List<PromotionDTO> list = promotionService.getAllPromotions()
                .stream()
                .map(promotion -> PromotionMapper.INSTANCE.toPromotionDto(promotion))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionDTO> updatePromotion (@PathVariable Long id, @RequestBody PromotionDTO promotionDTOUpdate){
        Promotion promotion = PromotionMapper.INSTANCE.toPromotion(promotionDTOUpdate);
        Promotion promotionUpdate = promotionService.updatePromotion(id,promotion);
        return ResponseEntity.ok(PromotionMapper.INSTANCE.toPromotionDto(promotionUpdate));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable Long id){
        String deletePromotion = promotionService.deletePromotion(id);
        return ResponseEntity.ok(deletePromotion);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Promotion>> getActivePromotions() {
        List<Promotion> activePromotions = promotionService.getActivePromotions();
        return ResponseEntity.ok(activePromotions);
    }

//    @GetMapping("/active1")
//    public ResponseEntity<List<Promotion>> getActive(){
//        return ResponseEntity.ok(promotionService.getActivePromotions1());
//    }


    @GetMapping("/discount")
    public ResponseEntity<BigDecimal> getDiscount(@RequestParam String promotionCode, @RequestParam Long courseId) {
        // Retrieve the course by ID (assuming you have a courseService to fetch the course)
        Course course = courseService.getCourseById(courseId);

        // Calculate the discount using the promotion service
        BigDecimal discount = promotionService.getDiscount(promotionCode, course);

        return ResponseEntity.ok(discount);
    }


    @PostMapping("/apply")
    public ResponseEntity<List<Promotion>> applyPromotion( @RequestParam String promotionCode, @RequestParam Long userId) {
        List<Promotion> promotions = promotionService.applyPromotion(promotionCode, userId);
        return ResponseEntity.ok(promotions);
    }

}
