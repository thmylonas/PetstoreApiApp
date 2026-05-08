package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PetStatusTypeValidator implements ConstraintValidator<ValidatePetStatusType, String> {

    @Override
    public boolean isValid(String petStatusType, ConstraintValidatorContext constraintValidatorContext) {
        final List<String> petStatusTypes = List.of("available", "pending", "sold");
        return petStatusTypes.contains(petStatusType);
    }
}
