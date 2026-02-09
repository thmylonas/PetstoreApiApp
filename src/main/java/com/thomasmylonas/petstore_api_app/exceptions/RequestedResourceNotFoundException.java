package com.thomasmylonas.petstore_api_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RequestedResourceNotFoundException extends RuntimeException {

    public RequestedResourceNotFoundException(Long resourceId) {
        super("The resource with ID " + resourceId + " is not found!");
    }

    public RequestedResourceNotFoundException(String message) {
        super(message);
    }
}
