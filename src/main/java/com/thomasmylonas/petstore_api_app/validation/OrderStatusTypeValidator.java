package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class OrderStatusTypeValidator implements ConstraintValidator<ValidateOrderStatusType, String> {

    @Override
    public boolean isValid(String petStatusType, ConstraintValidatorContext constraintValidatorContext) {
        final List<String> petStatusTypes = List.of("placed", "approved", "delivered");
        return petStatusTypes.contains(petStatusType.toLowerCase());
    }
}
