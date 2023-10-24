package org.mmm.challengegrogurides.infrastructure.controller.filter;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Data from rented vehicle")
public record RentVehicleDto(
        @Schema(description = "Rent id in UUID format", example = "af20972c-8da4-4fc3-9b53-359d64aa69fa") String id,
        @Schema(description = "Vehicle id in UUID format", example = "caf85f4b-69d9-49f6-906d-7af8d10f3f78") String vehicleId,
        @Schema(description = "User id in UUID format", example = "4a3c6513-0204-4dda-8f0c-eff8eb58e34b") String userId,
        @Schema(description = "Time until the vehicle will be rented") LocalDateTime endTime,
        @Schema(description = "Time when the vehicle was dropped off") LocalDateTime returnTime
) {}
