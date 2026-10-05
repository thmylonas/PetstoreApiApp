package com.thomasmylonas.petstore_api_app.api.controllers.advice_controllers;

import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseError;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandlerController {

    private final ResponseBuilder responseBuilder;

    @ExceptionHandler(value = {RequestedResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // 404: "Not Found"
    public ResponseEntity<ResponseError> handleRequestedResourceNotFoundException(RequestedResourceNotFoundException e, WebRequest webRequest) {
        String message = "The resource is not found: " + e.getMessage();
        return responseBuilder.buildResponseError(e, HttpStatus.NOT_FOUND, Map.of("error_message", message), webRequest);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400: "Bad Request"
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest webRequest) {

        Map<String, String> fieldErrorsMap = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrorsMap.put(fieldName, errorMessage);
        });
        return responseBuilder.buildResponseError(e, HttpStatus.BAD_REQUEST, Map.of("field_errors", fieldErrorsMap), webRequest);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400: "Bad Request"
    public ResponseEntity<ResponseError> handleConstraintViolationException(ConstraintViolationException e, WebRequest webRequest) {

        Map<String, String> constraintViolationsMap = new HashMap<>();
        e.getConstraintViolations().forEach(constraintViolation -> {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String violationMessage = constraintViolation.getMessage();
            constraintViolationsMap.put(propertyPath, violationMessage);
        });
        return responseBuilder.buildResponseError(e, HttpStatus.BAD_REQUEST, Map.of("constraint_violations", constraintViolationsMap), webRequest);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400: "Bad Request"
    public ResponseEntity<ResponseError> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest webRequest) {
        final String message = "Required request body is missing! - " + e.getMessage();
        return responseBuilder.buildResponseError(e, HttpStatus.BAD_REQUEST, Map.of("error_message", message), webRequest);
    }
}
