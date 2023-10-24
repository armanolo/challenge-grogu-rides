package org.mmm.challengegrogurides.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mmm.challengegrogurides.application.service.CreateUserService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
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
@Tag(name = "User operations")
@RestController
@Validated
@RequestMapping("/user")
public class CreateUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserController.class);
    private final CreateUserService setUserService;

    public CreateUserController(CreateUserService setUserService) {
        this.setUserService = setUserService;
    }

    @Operation(summary = "Creation of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create user"),
            @ApiResponse(responseCode = "400", description = "Invalid user",content = @Content),
            @ApiResponse(responseCode = "400", description = "Duplicate dni",content = @Content)
    })
    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postUser(
            @RequestBody UserDto userDto
    ) {
        setUserService.execute(userDto);
        LOGGER.info("User created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
