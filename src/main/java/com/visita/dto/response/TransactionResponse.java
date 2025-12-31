package com.visita.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    private String transactionId;
    private String userId;
    private String userName;
    private String userEmail;
    private String userAvatar;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String status;
    private String paymentMethod;
}
