package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

/**
 * TODO: All the entities are implemented wrongly
 */
public enum EnumStatus {

    AVAILABLE("0"),
    PENDING("1"),
    SOLD("2");

    String value;

    EnumStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
