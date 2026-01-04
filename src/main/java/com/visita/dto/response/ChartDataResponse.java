package com.visita.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartDataResponse {

    private String label; // e.g., "Jan 2024" or "2024-01"
    private BigDecimal value;
}
