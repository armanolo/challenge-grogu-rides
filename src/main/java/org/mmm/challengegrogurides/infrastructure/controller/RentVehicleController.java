package org.mmm.challengegrogurides.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mmm.challengegrogurides.application.service.OrderRentVehicleService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Tag(name = "Rent vehicles operations")
@RestController
@Validated
@RequestMapping("/rent")
public class RentVehicleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentVehicleController.class);
    private final OrderRentVehicleService rentVehicleService;

    public RentVehicleController(OrderRentVehicleService rentVehicleService) {
        this.rentVehicleService = rentVehicleService;
    }
    @Operation(summary = "Order rent")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordered rent"),
            @ApiResponse(responseCode = "202", description = "Rent requested but not found vehicle to rent"),
            @ApiResponse(responseCode = "400", description = "Invalid order rent",content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid user id",content = @Content)
    })
    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentVehicleDto> orderRent(
            @RequestBody OrderRentDto orderRentDto
    ) {
        RentVehicleDto rentVehicleDto = rentVehicleService.execute(orderRentDto);
        if (rentVehicleDto == null) {
            LOGGER.info("Vehicles was requested to rent but not possible to rent");
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        LOGGER.info("Vehicles rented");
        return new ResponseEntity<>(rentVehicleDto, HttpStatus.OK);
    }
}
