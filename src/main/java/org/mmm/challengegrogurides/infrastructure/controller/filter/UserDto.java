package org.mmm.challengegrogurides.infrastructure.controller.filter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data to create a new user")
public record UserDto(
        @Schema(description = "Id in UUID format", example = "4a3c6513-0204-4dda-8f0c-eff8eb58e34b") String id,
        @Schema(description = "NIF/DNI", example = "11161412H") String dni,
        @Schema(description = "Full name", example = "Manuel Martin") String name) {
}
