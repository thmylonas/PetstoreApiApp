package com.thomasmylonas.petstore_api_web_app.entities;

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
