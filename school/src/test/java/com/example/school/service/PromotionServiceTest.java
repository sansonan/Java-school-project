package com.example.school.service;

import com.example.school.entity.Promotion;
import com.example.school.entity.User;
import com.example.school.repository.PromotionRepository;
import com.example.school.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionServiceImpl promotionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreatePromotion() {
        Promotion pro = new Promotion();
        pro.setTitle("Test Course");

        when(promotionRepository.save(any(Promotion.class))).thenReturn(pro);

        Promotion createdPro = promotionService.createPromotion(pro);

        assertNotNull(createdPro);
        assertEquals("Test Course", createdPro.getTitle());

        verify(promotionRepository, times(1)).save(any(Promotion.class));
    }

    @Test
    void testGetPromotionById() {
        Long promotionId = 1L;
        Promotion promotion = new Promotion();
        promotion.setId(promotionId);
        promotion.setTitle("Test Course");

        when(promotionRepository.findById(promotionId)).thenReturn(Optional.of(promotion));

        Promotion retrievedPro = promotionService.getPromotionById(promotionId);

        assertNotNull(retrievedPro);
        assertEquals(promotionId, retrievedPro.getId());
        assertEquals("Test Course", retrievedPro.getTitle());

        verify(promotionRepository, times(1)).findById(promotionId);
    }

    @Test
    void testGetActivePromotions() {
        // Arrange
        Promotion promo1 = new Promotion();
        promo1.setTitle("Promo1");
        promo1.setStatus("active");

        Promotion promo2 = new Promotion();
        promo2.setTitle("Promo2");
        promo2.setStatus("active");

        List<Promotion> activePromotions = Arrays.asList(promo1, promo2);

        when(promotionRepository.findByStatus("active")).thenReturn(activePromotions);

        // Act
        List<Promotion> result = promotionService.getActivePromotions();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Promo1", result.get(0).getTitle());
        assertEquals("Promo2", result.get(1).getTitle());
    }

    @Test
    void testGetAllPromotion() {
        Promotion promotion1 = new Promotion();
        promotion1.setTitle("Promotion1");

        Promotion promotion2 = new Promotion();
        promotion2.setTitle("Promotion2");
        List<Promotion> promotions = Arrays.asList(promotion1, promotion2);
        when(promotionRepository.findAll()).thenReturn(promotions);
        List<Promotion> retrievedPromo = promotionService.getAllPromotions();
        assertNotNull(retrievedPromo);
        assertEquals(2, retrievedPromo.size());
        assertEquals("Promotion1", retrievedPromo.get(0).getTitle());
        assertEquals("Promotion2", retrievedPromo.get(1).getTitle());

        verify(promotionRepository, times(1)).findAll();
    }

    @Test
    void testUpdatePermission() {
        // Arrange
        Promotion existingPromotion = new Promotion();
        existingPromotion.setId(1L);
        existingPromotion.setTitle("Promotion3");

        Promotion updatedPromotionData = new Promotion();
        updatedPromotionData.setTitle("PROMOTION_UPDATE");

        when(promotionRepository.findById(1L)).thenReturn(Optional.of(updatedPromotionData));
        when(promotionRepository.save(any(Promotion.class))).thenReturn(updatedPromotionData);

        // Act
        Promotion updatedPromotion = promotionService.updatePromotion(1L, updatedPromotionData);

        // Assert
        assertEquals("PROMOTION_UPDATE", updatedPromotion.getTitle());
    }

    @Test
    void testDeletePromotion() {
        // Arrange
        Promotion promotion = new Promotion();
        promotion.setId(1L);
        promotion.setTitle("promotiontest");
        promotion.setStatus("active");


        when(promotionRepository.findById(promotion.getId())).thenReturn(Optional.of(promotion));
        doNothing().when(promotionRepository).delete(promotion);

        // Act
        promotionService.deletePromotion(promotion.getId());

        // Assert
        verify(promotionRepository, times(1)).delete(promotion);
    }


}

