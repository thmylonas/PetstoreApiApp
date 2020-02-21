package com.thomasmylonas.petstore_api_web_app.controllers.handlers;

import com.thomasmylonas.petstore_api_web_app.controllers.BaseController;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.models.response_status_models.ErrorStatus;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionsHandlerController extends BaseController {

    @Autowired
    private ErrorStatus errorStatus;

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public ErrorStatus resourceNotFound(ResourceNotFoundException e) {

        long resourceId = e.getResourceId();
        String message = String.format(e.getMyMessage(), resourceId);
        e.setMyMessage(message);
        setResponseStatus(errorStatus, e, HttpStatus.NOT_FOUND, e.getMyMessage());
        return errorStatus;
    }

    @ExceptionHandler(value = {InvalidInputSuppliedException.class})
    @ResponseBody
    public ErrorStatus invalidInputSupplied(InvalidInputSuppliedException e) {
        e.setMyMessage(e.getMyMessage());
        setResponseStatus(errorStatus, e, HttpStatus.BAD_REQUEST, e.getMyMessage());
        return errorStatus;
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // METHOD_NOT_ALLOWED(405, "Method Not Allowed")
    public ErrorStatus invalidBodyInput(HttpMessageNotReadableException e) {
        setResponseStatus(errorStatus, e, HttpStatus.METHOD_NOT_ALLOWED, "Invalid input");
        return errorStatus;
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE) // UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type")
    public ErrorStatus mediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        setResponseStatus(errorStatus, e, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Media type not supported");
        return errorStatus;
    }
}
