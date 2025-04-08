package com.thomasmylonas.petstore_api_web_app.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PetStatusEnum {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;

    public static PetStatusEnum fromValue(String value) {
        return switch (value) {
            case "available" -> PetStatusEnum.AVAILABLE;
            case "pending" -> PetStatusEnum.PENDING;
            case "sold" -> PetStatusEnum.SOLD;
            default -> null;
        };
    }

    public static boolean isPetStatus(String status) {
        return Arrays.stream(PetStatusEnum.values())
                .anyMatch(petStatus -> petStatus.getValue().equals(status.toLowerCase()));
    }
}
