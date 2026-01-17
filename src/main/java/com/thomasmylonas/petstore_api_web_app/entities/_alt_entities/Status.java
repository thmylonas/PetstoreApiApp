package com.thomasmylonas.petstore_api_web_app.entities._alt_entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    AVAILABLE("Available"),
    PENDING("Pending"),
    SOLD("Sold");

    private final String value;
}
