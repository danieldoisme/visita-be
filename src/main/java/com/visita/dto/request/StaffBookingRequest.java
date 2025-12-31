package com.visita.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffBookingRequest {

    @NotBlank(message = "USER_ID_REQUIRED")
    String userId;

    @NotBlank(message = "TOUR_ID_REQUIRED")
    String tourId;

    @NotNull(message = "NUM_ADULTS_REQUIRED")
    @Min(value = 1, message = "At least 1 adult required")
    Integer numAdults;

    @Min(value = 0, message = "Children count cannot be negative")
    Integer numChildren;

    String specialRequest;

    String promotionCode;
}
