package com.thomasmylonas.petstore_api_web_app.exception_handlers.handlers;

import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.models.ErrorStatus;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionsHandlerController {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionsHandlerController.class.getName());

    @Autowired
    private ErrorStatus errorStatus;

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public ErrorStatus resourceNotFound(ResourceNotFoundException e) {

        long resourceId = e.getResourceId();
        String message = String.format(e.getMyMessage(), resourceId);
        e.setMyMessage(message);

        setErrorStatus(e, HttpStatus.NOT_FOUND, "");
        return errorStatus;
    }

    @ExceptionHandler(value = {InvalidInputSuppliedException.class})
    @ResponseBody
    public ErrorStatus invalidInputSupplied(InvalidInputSuppliedException e) {
        e.setMyMessage(e.getMyMessage());
        setErrorStatus(e, HttpStatus.BAD_REQUEST, ": " + e.getMyMessage());
        return errorStatus;
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // METHOD_NOT_ALLOWED(405, "Method Not Allowed")
    public ErrorStatus invalidBodyInput(HttpMessageNotReadableException e) {
        setErrorStatus(e, HttpStatus.METHOD_NOT_ALLOWED, ": " + "Invalid input");
        return errorStatus;
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE) // UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type")
    public ErrorStatus mediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        setErrorStatus(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE, ": " + "Media type not supported");
        return errorStatus;
    }

    public void setErrorStatus(Exception e, HttpStatus httpStatus, String message) {
        errorStatus.setErrorDescription(httpStatus.toString() + message);
        String errorDescription = errorStatus.getErrorDescription();
//        errorStatus.setErrorCode(Integer.parseInt(errorDescription.split(" ")[0]));
        errorStatus.setErrorCode(Integer.parseInt(errorDescription.substring(0, errorDescription.indexOf(" "))));
        errorStatus.setMessage(new ResponseEntity<>(e, httpStatus).getBody().getMessage());

        LOGGER.info(errorDescription);
    }
}
