package com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputSuppliedException extends BaseRuntimeException {

    public InvalidInputSuppliedException(String message) {
        super(message);
        myMessage = message;
    }
}
