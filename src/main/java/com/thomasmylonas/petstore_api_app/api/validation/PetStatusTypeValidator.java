package com.thomasmylonas.petstore_api_app.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class PetStatusTypeValidator implements ConstraintValidator<ValidatePetStatusType, String> {

    @Override
    public boolean isValid(String petStatusType, ConstraintValidatorContext constraintValidatorContext) {

        if (petStatusType == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The 'PetStatusType' must not be null").addConstraintViolation();
            return false;
        }
        final List<String> petStatusTypes = new ArrayList<>(List.of("available", "pending", "sold"));
        return petStatusTypes.contains(petStatusType.toLowerCase());
    }
}
