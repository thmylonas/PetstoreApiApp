package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    AVAILABLE("Available"),
    PENDING("Pending"),
    SOLD("Sold");

    private final String value;
}
