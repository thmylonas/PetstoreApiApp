package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UserStatusTypeValidator implements ConstraintValidator<ValidateUserStatusType, Integer> {

    @Override
    public boolean isValid(Integer userStatusTypeType, ConstraintValidatorContext constraintValidatorContext) {
        final List<Integer> userStatusTypeTypes = List.of(0, 1);
        return userStatusTypeTypes.contains(userStatusTypeType);
    }
}
