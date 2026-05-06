package com.thomasmylonas.petstore_api_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserStatus {

    USER_STATUS_1(0),
    USER_STATUS_2(1);

    private final int value;

    public static UserStatus valueOfUserStatus(int status) {
        return Arrays.stream(values())
                .filter(userStatus -> userStatus.getValue() == status)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The status argument in not valid!"));
    }
}
