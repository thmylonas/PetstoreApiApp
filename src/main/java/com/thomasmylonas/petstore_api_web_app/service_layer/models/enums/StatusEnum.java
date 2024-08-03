package com.thomasmylonas.petstore_api_web_app.service_layer.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;
}
