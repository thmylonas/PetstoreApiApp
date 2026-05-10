package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class BooleanTypeValidator implements ConstraintValidator<ValidateBooleanType, Boolean> {

    @Override
    public boolean isValid(Boolean booleanType, ConstraintValidatorContext constraintValidatorContext) {

        if (booleanType == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The 'BooleanType' must not be null").addConstraintViolation();
            return false;
        }
        final List<Boolean> booleanTypes = new ArrayList<>(List.of(true, false));
        return booleanTypes.contains(booleanType);
    }
}
