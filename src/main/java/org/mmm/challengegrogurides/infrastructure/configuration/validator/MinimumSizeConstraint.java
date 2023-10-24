package org.mmm.challengegrogurides.infrastructure.configuration.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinimumSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinimumSizeConstraint {
    String message() default "The vehicle list must contain more than 1 vehicle.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
