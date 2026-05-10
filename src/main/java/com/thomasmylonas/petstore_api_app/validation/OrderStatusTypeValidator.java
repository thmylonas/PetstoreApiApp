package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusTypeValidator implements ConstraintValidator<ValidateOrderStatusType, String> {

    @Override
    public boolean isValid(String orderStatusType, ConstraintValidatorContext constraintValidatorContext) {

        if (orderStatusType == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The 'OrderStatusType' must not be null").addConstraintViolation();
            return false;
        }
        final List<String> orderStatusTypes = new ArrayList<>(List.of("placed", "approved", "delivered"));
        return orderStatusTypes.contains(orderStatusType.toLowerCase());
    }
}
