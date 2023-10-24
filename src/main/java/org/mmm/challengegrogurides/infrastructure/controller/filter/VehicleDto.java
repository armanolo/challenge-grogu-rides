package org.mmm.challengegrogurides.infrastructure.controller.filter;


import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Vehicle data")
public record VehicleDto(
        @Schema(description = "Vehicle id in UUID format", example = "caf85f4b-69d9-49f6-906d-7af8d10f3f78") String id,
        @Schema(description = "Available seats for the vehicle", example = "3") int seats) {}
