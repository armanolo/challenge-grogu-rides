package org.mmm.challengegrogurides.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mmm.challengegrogurides.application.service.GetUserService;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Tag(name = "User operations")
@RestController
@Validated
@RequestMapping("/user")
public class GetUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserController.class);
    private final GetUserService getUserService;

    public GetUserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @Operation(summary = "Get user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get user",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema( implementation = UserDto.class))
                        ),
            @ApiResponse(responseCode = "400", description = "Invalid user id",content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found user",content = @Content)
    })
    @CrossOrigin
    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> getUser(
            @Parameter(description = "Id in UUID format", example = "4a3c6513-0204-4dda-8f0c-eff8eb58e34b")
            @PathVariable String uuid) {
        UserDto userDto = getUserService.execute(uuid);
        LOGGER.info("get User");
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
