package org.mmm.challengegrogurides.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mmm.challengegrogurides.application.service.SetVehicleFleetService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
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

import java.util.List;
@CrossOrigin
@Tag(name = "Vehicles operations")
@RestController
@Validated
@RequestMapping("/vehicles")
public class SetVehicleFleetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SetVehicleFleetController.class);
    private final SetVehicleFleetService setVehicleFleetService;

    public SetVehicleFleetController(SetVehicleFleetService setVehicleFleetService) {
        this.setVehicleFleetService = setVehicleFleetService;
    }
    @Operation(summary = "Creation of vehicle list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create vehicle list"),
            @ApiResponse(responseCode = "400", description = "Invalid Vehicle id",content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid seats",content = @Content)
    })
    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postVehicles(
            @RequestBody //@NotEmpty(message = "Input vehicle list cannot be empty.")
            //@MinimumSizeConstraint
            List<VehicleDto> vehicleDtoList
    ) {
        setVehicleFleetService.execute(vehicleDtoList);
        LOGGER.info("Vehicles created");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
