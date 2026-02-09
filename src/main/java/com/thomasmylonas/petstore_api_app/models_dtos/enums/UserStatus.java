package com.thomasmylonas.petstore_api_app.models_dtos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    USER_STATUS_1(0),
    USER_STATUS_2(1);

    private final int value;
}
