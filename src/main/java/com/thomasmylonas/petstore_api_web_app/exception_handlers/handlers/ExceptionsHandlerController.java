package com.thomasmylonas.petstore_api_web_app.exception_handlers.handlers;

import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.models.ErrorStatus;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionsHandlerController {

    @Autowired
    private ErrorStatus errorStatus;

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseBody
    public ErrorStatus resourceNotFound(ResourceNotFoundException e) {

        long resourceId = e.getResourceId();
        String message = String.format(e.getMyMessage(), resourceId);
        e.setMyMessage(message);

        errorStatus.setErrorDescription(HttpStatus.NOT_FOUND.toString());
        String errorDescription = errorStatus.getErrorDescription();
//        errorStatus.setErrorCode(Integer.parseInt(errorDescription.split(" ")[0]));
        errorStatus.setErrorCode(Integer.parseInt(errorDescription.substring(0, errorDescription.indexOf(" "))));
        errorStatus.setMessage(new ResponseEntity<>(e, HttpStatus.NOT_FOUND).getBody().getMyMessage());

        return errorStatus;
    }

    @ExceptionHandler(value = {InvalidInputSuppliedException.class})
    @ResponseBody
    public ErrorStatus invalidInputSupplied(InvalidInputSuppliedException e) {

        e.setMyMessage(e.getMyMessage());

        errorStatus.setErrorDescription(HttpStatus.BAD_REQUEST.toString() + ": " + e.getMyMessage());
        String errorDescription = errorStatus.getErrorDescription();
//        errorStatus.setErrorCode(Integer.parseInt(errorDescription.split(" ")[0]));
        errorStatus.setErrorCode(Integer.parseInt(errorDescription.substring(0, errorDescription.indexOf(" "))));
        errorStatus.setMessage(new ResponseEntity<>(e, HttpStatus.BAD_REQUEST).getBody().getMyMessage());

        return errorStatus;
    }
}
