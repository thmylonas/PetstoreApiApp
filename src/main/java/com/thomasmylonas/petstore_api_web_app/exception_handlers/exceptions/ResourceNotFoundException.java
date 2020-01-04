package com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseRuntimeException {

    private long resourceId;

    public ResourceNotFoundException(long resourceId, String message) {
        super(message);
        super.setMyMessage(message);
        this.resourceId = resourceId;
    }

    public long getResourceId() {
        return resourceId;
    }
}
