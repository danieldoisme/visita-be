package com.visita.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardStatsResponse {
    private BigDecimal totalRevenue;
    private Double revenueGrowth; // Percentage vs last month

    private Long newUsers;
    private Double userGrowth;

    private Long totalBookings;
    private Double bookingGrowth;

    private Long activeUsers;
}
