package com.thomasmylonas.petstore_api_app.controllers.advice_controllers;

import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseError;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // 404, "Not Found"
    public ResponseEntity<ResponseError> handleRequestedResourceNotFoundException(RequestedResourceNotFoundException e, WebRequest webRequest) {
        return responseBuilder.buildResponseError(e, HttpStatus.NOT_FOUND, Map.of("error_message", e.getMessage()), webRequest);
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

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // 405, "Method Not Allowed"
    public ResponseEntity<ResponseError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, WebRequest webRequest) {
        final String message = String.format("Request method '%s' is not supported! - %s", e.getMethod(), e.getMessage());
        return responseBuilder.buildResponseError(e, HttpStatus.METHOD_NOT_ALLOWED, Map.of("error_message", message), webRequest);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE) // 415, "Unsupported Media Type"
    public ResponseEntity<ResponseError> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, WebRequest webRequest) {
        final String message = String.format("Content-Type '%s' is not supported! - %s", e.getContentType(), e.getMessage()); // Content-Type 'application/xml;charset=UTF-8' is not supported
        return responseBuilder.buildResponseError(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE, Map.of("error_message", message), webRequest);
    }

    /*@ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // BAD_REQUEST(400, "Bad Request")
    public ResponseSuccess invalidInputSupplied(IllegalArgumentException e) {
//        e.setMyMessage(e.getMyMessage());
//        setResponseStatus(errorStatus, e, HttpStatus.BAD_REQUEST, e.getMyMessage());
        return null;
    }*/
}
