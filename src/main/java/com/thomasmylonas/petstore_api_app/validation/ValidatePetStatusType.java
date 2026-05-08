package com.thomasmylonas.petstore_api_app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The following implementation is according the implementation of all the "Built-In_Validation_Annotations"
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PetStatusTypeValidator.class)
@Documented
public @interface ValidatePetStatusType {

    String message() default "Invalid 'PetStatusType': It should be either 'available' or 'pending' or 'sold'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
