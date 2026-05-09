package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UserStatusTypeValidator implements ConstraintValidator<ValidateUserStatusType, Integer> {

    @Override
    public boolean isValid(Integer userStatusType, ConstraintValidatorContext constraintValidatorContext) {
        final List<Integer> userStatusTypes = List.of(0, 1);
        return userStatusTypes.contains(userStatusType);
    }
}
