package org.mmm.challengegrogurides.infrastructure.configuration.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;

import java.util.List;

public class MinimumSizeConstraintValidator implements ConstraintValidator<MinimumSizeConstraint, List<VehicleDto>> {
    @Override
    public boolean isValid(List<VehicleDto> values, ConstraintValidatorContext context) {
        return values.size() > 1;
    }
}
