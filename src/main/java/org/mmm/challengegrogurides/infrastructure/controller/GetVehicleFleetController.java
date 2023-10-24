package org.mmm.challengegrogurides.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mmm.challengegrogurides.application.service.GetVehicleFleetService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Tag(name = "Vehicles operations")
@RestController
@Validated
@RequestMapping("/vehicles")
public class GetVehicleFleetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetVehicleFleetController.class);
    private final GetVehicleFleetService getVehicleFleetService;

    public GetVehicleFleetController(GetVehicleFleetService getVehicleFleetService) {
        this.getVehicleFleetService = getVehicleFleetService;
    }

    @Operation(summary = "Get vehicle list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get vehicle list")
    })
    @CrossOrigin
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VehicleDto>> getVehicles() {
        List<VehicleDto> vehicleDtoList = getVehicleFleetService.execute();
        LOGGER.info("requested Vehicles");
        return new ResponseEntity<>(vehicleDtoList, HttpStatus.OK);
    }
}
