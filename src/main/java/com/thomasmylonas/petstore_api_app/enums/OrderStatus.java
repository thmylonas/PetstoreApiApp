package com.thomasmylonas.petstore_api_app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private final String value;

    public static OrderStatus valueOfOrderStatus(String status) {
        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.getValue().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The status argument in not valid!"));
    }
}
