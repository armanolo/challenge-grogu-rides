package org.mmm.challengegrogurides.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mmm.challengegrogurides.application.service.DropOffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Tag(name = "Rent vehicles operations")
@RestController
@Validated
@RequestMapping("/rent")
public class DropOffController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DropOffController.class);
    private final DropOffService dropOffService;

    public DropOffController(DropOffService dropOffService) {
        this.dropOffService = dropOffService;
    }
    @Operation(summary = "Return rent")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drop off vehicle"),
            @ApiResponse(responseCode = "400", description = "Invalid information",content = @Content),
    })
    @CrossOrigin
    @PostMapping("/{uuid}/drop-off")
    public ResponseEntity<Void> dropOff(
            @Parameter(description = "Rent id in UUID format", example = "4a3c6513-0204-4dda-8f0c-eff8eb58e34b")
            @PathVariable String uuid
    ) {
        dropOffService.execute(uuid);
        LOGGER.info("drop off vehicle");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
