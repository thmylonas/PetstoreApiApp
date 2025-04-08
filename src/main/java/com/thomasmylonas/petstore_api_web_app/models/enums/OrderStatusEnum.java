package com.thomasmylonas.petstore_api_web_app.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private final String value;
}
