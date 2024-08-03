package com.thomasmylonas.petstore_api_web_app.service_layer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long resourceId) {
        super("The resource with ID " + resourceId + " is not found!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
