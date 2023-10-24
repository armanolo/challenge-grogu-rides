package org.mmm.challengegrogurides.infrastructure.configuration.exception;

import org.springframework.hateoas.EntityModel;

public class ErrorHateoasResponseBody extends EntityModel<ErrorHateoasResponseBody> {

    private String errorMessage;

    public ErrorHateoasResponseBody(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    /*
    ErrorHateoasResponseBody errorResponse = new ErrorHateoasResponseBody(ex.getMessage());
    // Add custom links to the error response
    Link selfLink = WebMvcLinkBuilder.linkTo(SetVehicleFleetController.class)
            .slash("error")
            .withSelfRel();
    errorResponse.add(selfLink);

    For controller
    Resource resource = getResourceById(id);
    // Add HATEOAS links to the resource
    EntityModel<Resource> resourceModel = EntityModel.of(resource);
    resourceModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ApiController.class)
            .getResource(id)).withSelfRel());
    // Add more links if needed

    return ResponseEntity.ok(resourceModel);
     */
}
