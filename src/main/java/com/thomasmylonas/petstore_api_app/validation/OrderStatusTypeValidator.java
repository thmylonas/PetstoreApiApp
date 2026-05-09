package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class OrderStatusTypeValidator implements ConstraintValidator<ValidateOrderStatusType, String> {

    @Override
    public boolean isValid(String orderStatusType, ConstraintValidatorContext constraintValidatorContext) {
        final List<String> orderStatusTypes = List.of("placed", "approved", "delivered");
        return orderStatusTypes.contains(orderStatusType.toLowerCase());
    }
}
