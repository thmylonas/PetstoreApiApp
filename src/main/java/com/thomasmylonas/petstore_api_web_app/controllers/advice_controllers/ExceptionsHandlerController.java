package com.thomasmylonas.petstore_api_web_app.controllers.advice_controllers;

import com.thomasmylonas.petstore_api_web_app.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionsHandlerController {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // NOT_FOUND(404, "Not Found")
    public ResponseStatus resourceNotFound(ResourceNotFoundException e) {

//        long resourceId = e.getResourceId();
//        String message = String.format(e.getMyMessage(), resourceId);
//        e.setMyMessage(message);
//        setResponseStatus(errorStatus, e, HttpStatus.NOT_FOUND, e.getMyMessage());
        return null;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // BAD_REQUEST(400, "Bad Request")
    public ResponseStatus invalidInputSupplied(IllegalArgumentException e) {
//        e.setMyMessage(e.getMyMessage());
//        setResponseStatus(errorStatus, e, HttpStatus.BAD_REQUEST, e.getMyMessage());
        return null;
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // METHOD_NOT_ALLOWED(405, "Method Not Allowed")
    public ResponseStatus invalidBodyInput(HttpMessageNotReadableException e) {
//        setResponseStatus(errorStatus, e, HttpStatus.METHOD_NOT_ALLOWED, "Invalid input");
        return null;
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE) // UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type")
    public ResponseStatus mediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
//        setResponseStatus(errorStatus, e, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Media type not supported");
        return null;
    }
}
