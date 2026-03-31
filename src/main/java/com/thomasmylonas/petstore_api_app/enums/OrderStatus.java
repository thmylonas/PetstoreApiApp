package com.thomasmylonas.petstore_api_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private final String value;
}
