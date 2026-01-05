package com.visita.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.visita.dto.request.PromotionRequest;
import com.visita.entities.PromotionEntity;
import com.visita.exceptions.ErrorCode;
import com.visita.exceptions.WebException;
import com.visita.repositories.PromotionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionEntity createPromotion(PromotionRequest request) {
        if (promotionRepository.existsByCode(request.getCode())) {
            throw new WebException(ErrorCode.PROMOTION_EXISTED);
        }

        if (request.getStartDate() != null && request.getEndDate() != null
                && request.getStartDate().isAfter(request.getEndDate())) {
            throw new WebException(ErrorCode.END_DATE_AFTER_START_DATE);
        }

        PromotionEntity promotion = PromotionEntity.builder()
                .code(request.getCode())
                .description(request.getDescription())
                .discountAmount(request.getDiscountAmount())
                .discountPercent(request.getDiscountPercent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .quantity(request.getQuantity())
                .build();

        return promotionRepository.save(promotion);
    }

    public PromotionEntity updatePromotion(String id, PromotionRequest request) {
        PromotionEntity promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new WebException(ErrorCode.PROMOTION_NOT_FOUND));

        if (request.getStartDate() != null && request.getEndDate() != null
                && request.getStartDate().isAfter(request.getEndDate())) {
            throw new WebException(ErrorCode.END_DATE_AFTER_START_DATE);
        }

        promotion.setCode(request.getCode());
        promotion.setDescription(request.getDescription());
        promotion.setDiscountAmount(request.getDiscountAmount());
        promotion.setDiscountPercent(request.getDiscountPercent());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setQuantity(request.getQuantity());

        return promotionRepository.save(promotion);
    }

    public void deletePromotion(String id) {
        if (!promotionRepository.existsById(id)) {
            throw new WebException(ErrorCode.PROMOTION_NOT_FOUND);
        }
        promotionRepository.deleteById(id);
    }

    public void updateStatus(String promotionId, boolean isActive) {
        PromotionEntity promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new WebException(ErrorCode.PROMOTION_NOT_FOUND));
        promotion.setIsActive(isActive);
        promotionRepository.save(promotion);
    }

    public List<PromotionEntity> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public com.visita.dto.response.PromoValidationResponse validatePromoCode(String code) {
        java.util.Optional<PromotionEntity> optionalPromo = promotionRepository.findByCode(code);

        if (optionalPromo.isEmpty()) {
            return com.visita.dto.response.PromoValidationResponse.builder()
                    .valid(false)
                    .message("Promotion code not found")
                    .build();
        }

        PromotionEntity promo = optionalPromo.get();

        if (!promo.getIsActive()) {
            return com.visita.dto.response.PromoValidationResponse.builder()
                    .valid(false)
                    .message("Promotion is inactive")
                    .build();
        }

        java.time.LocalDate today = java.time.LocalDate.now();
        if (promo.getStartDate() != null && today.isBefore(promo.getStartDate())) {
            return com.visita.dto.response.PromoValidationResponse.builder()
                    .valid(false)
                    .message("Promotion has not started yet")
                    .build();
        }

        if (promo.getEndDate() != null && today.isAfter(promo.getEndDate())) {
            return com.visita.dto.response.PromoValidationResponse.builder()
                    .valid(false)
                    .message("Promotion has expired")
                    .build();
        }

        if (promo.getQuantity() != null && promo.getQuantity() <= 0) {
            return com.visita.dto.response.PromoValidationResponse.builder()
                    .valid(false)
                    .message("Promotion is out of stock")
                    .build();
        }

        String discountType;
        java.math.BigDecimal discountValue;

        if (promo.getDiscountPercent() != null && promo.getDiscountPercent().compareTo(java.math.BigDecimal.ZERO) > 0) {
            discountType = "PERCENT";
            discountValue = promo.getDiscountPercent();
        } else {
            discountType = "AMOUNT";
            discountValue = promo.getDiscountAmount();
        }

        return com.visita.dto.response.PromoValidationResponse.builder()
                .valid(true)
                .discountType(discountType)
                .discountValue(discountValue)
                .description(promo.getDescription())
                .message("Promotion is valid")
                .build();
    }
}
