package com.visita.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visita.dto.request.PromoValidationRequest;
import com.visita.dto.response.ApiResponse;
import com.visita.dto.response.PromoValidationResponse;
import com.visita.services.PromotionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping("/validate")
    public ApiResponse<PromoValidationResponse> validatePromoCode(
            @RequestBody @Valid PromoValidationRequest request) {
        ApiResponse<PromoValidationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(promotionService.validatePromoCode(request.getCode()));
        return apiResponse;
    }
}
