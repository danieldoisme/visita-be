package com.visita.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromoValidationResponse {

    private boolean valid;
    private String discountType;
    private BigDecimal discountValue;
    private String description;
    private String message;
}
