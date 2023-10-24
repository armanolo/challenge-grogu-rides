package org.mmm.challengegrogurides.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mmm.challengegrogurides.application.service.GetRentVehicleService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Tag(name = "Rent vehicles operations")
@RestController
@Validated
@RequestMapping("/rent")
public class GetRentVehicleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetRentVehicleController.class);
    private final GetRentVehicleService getRentVehicleService;

    public GetRentVehicleController(GetRentVehicleService getRentVehicleService) {
        this.getRentVehicleService = getRentVehicleService;
    }
    @Operation(summary = "Get rent vehicles")
        @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordered rent")})
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<RentVehicleDto>> orderRent() {
        List<RentVehicleDto> rentVehicleDtoList = getRentVehicleService.execute();
        LOGGER.info("Get vehicles rented");
        return new ResponseEntity<>(rentVehicleDtoList, HttpStatus.OK);
    }
}
