package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

public enum StatusEnum {

    AVAILABLE("0"),
    PENDING("1"),
    SOLD("2");

    final String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
