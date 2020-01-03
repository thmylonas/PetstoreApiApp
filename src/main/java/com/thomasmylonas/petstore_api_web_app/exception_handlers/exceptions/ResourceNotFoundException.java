package com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private long resourceId;
    private String myMessage; // "message" in SuperClass not settable

    public ResourceNotFoundException(long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
        myMessage = message;
    }

    public long getResourceId() {
        return resourceId;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }
}
