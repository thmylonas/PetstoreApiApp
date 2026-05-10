package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class UserStatusTypeValidator implements ConstraintValidator<ValidateUserStatusType, Integer> {

    @Override
    public boolean isValid(Integer userStatusType, ConstraintValidatorContext constraintValidatorContext) {

        if (userStatusType == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("The 'UserStatusType' must not be null").addConstraintViolation();
            return false;
        }
        final List<Integer> userStatusTypes = new ArrayList<>(List.of(0, 1));
        return userStatusTypes.contains(userStatusType);
    }
}
