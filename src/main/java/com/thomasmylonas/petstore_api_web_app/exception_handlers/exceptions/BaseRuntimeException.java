package com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions;

public abstract class BaseRuntimeException extends RuntimeException {

    protected String myMessage; // "message" in SuperClass not settable

    public BaseRuntimeException(String message) {
        super(message);
        myMessage = message;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }
}
