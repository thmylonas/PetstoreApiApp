package com.thomasmylonas.petstore_api_app.controllers.advice_controllers;

//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.HttpMediaTypeNotSupportedException;

import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseError;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // NOT_FOUND(404, "Not Found")
    public ResponseEntity<ResponseError> handleRequestedResourceNotFoundException(RequestedResourceNotFoundException e, WebRequest webRequest) {
        return responseBuilder.buildResponseError(e, HttpStatus.NOT_FOUND, "", webRequest);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400: "Bad Request"
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> fieldErrorsMap = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrorsMap.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrorsMap);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400: "Bad Request"
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {

        Map<String, String> constraintViolationsMap = new HashMap<>();
        e.getConstraintViolations().forEach(constraintViolation -> {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String violationMessage = constraintViolation.getMessage();
            constraintViolationsMap.put(propertyPath, violationMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(constraintViolationsMap);
    }

    /*@ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // BAD_REQUEST(400, "Bad Request")
    public ResponseSuccess invalidInputSupplied(IllegalArgumentException e) {
//        e.setMyMessage(e.getMyMessage());
//        setResponseStatus(errorStatus, e, HttpStatus.BAD_REQUEST, e.getMyMessage());
        return null;
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // METHOD_NOT_ALLOWED(405, "Method Not Allowed")
    public ResponseSuccess invalidBodyInput(HttpMessageNotReadableException e) {
//        setResponseStatus(errorStatus, e, HttpStatus.METHOD_NOT_ALLOWED, "Invalid input");
        return null;
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE) // UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type")
    public ResponseSuccess mediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
//        setResponseStatus(errorStatus, e, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Media type not supported");
        return null;
    }*/
}
