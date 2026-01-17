package com.thomasmylonas.petstore_api_web_app.models_dtos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PetStatus {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;

    public static PetStatus fromValue(String value) {
        return switch (value) {
            case "available" -> PetStatus.AVAILABLE;
            case "pending" -> PetStatus.PENDING;
            case "sold" -> PetStatus.SOLD;
            default -> null;
        };
    }

    public static boolean isPetStatus(String status) {
        return Arrays.stream(PetStatus.values())
                .anyMatch(petStatus -> petStatus.getValue().equals(status.toLowerCase()));
    }
}
