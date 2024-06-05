package com.example.school.service.util;

import com.example.school.dto.PromotionDTO;
import com.example.school.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromotionMapper {

   PromotionMapper INSTANCE =  Mappers.getMapper(PromotionMapper.class);
   PromotionDTO toPromotionDto(Promotion promotion);
   Promotion toPromotion(PromotionDTO promotionDTO);

}
