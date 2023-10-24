package org.mmm.challengegrogurides.infrastructure.controller.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Data to order a rent vehicle")
public record OrderRentDto(
        @Schema(description = "User id in UUID format", example = "4a3c6513-0204-4dda-8f0c-eff8eb58e34b") String userId,
        @Schema(description = "Time until the vehicle will be rented") @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
        @Schema(description = "Required seats for the vehicle", example = "5") Integer seats
) {}
