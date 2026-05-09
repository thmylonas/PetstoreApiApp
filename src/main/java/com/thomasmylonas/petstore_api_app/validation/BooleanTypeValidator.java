package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class BooleanTypeValidator implements ConstraintValidator<ValidateBooleanType, Boolean> {

    @Override
    public boolean isValid(Boolean booleanType, ConstraintValidatorContext constraintValidatorContext) {
        final List<Boolean> booleanTypes = List.of(true, false);
        return booleanTypes.contains(booleanType);
    }
}
