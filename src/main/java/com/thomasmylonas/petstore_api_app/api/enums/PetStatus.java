package com.thomasmylonas.petstore_api_app.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PetStatus {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;

    public static boolean isPetStatus(String status) {
        return Arrays.stream(PetStatus.values())
                .anyMatch(petStatus -> petStatus.getValue().equals(status.toLowerCase()));
    }

    public static PetStatus valueOfPetStatus(String status) {
        return Arrays.stream(values())
                .filter(petStatus -> petStatus.getValue().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The status argument in not valid!"));
    }

    public static PetStatus valueOfPetStatusAlt(String status) {
        return switch (status) {
            case "available" -> AVAILABLE;
            case "pending" -> PENDING;
            case "sold" -> SOLD;
            default -> throw new IllegalArgumentException("The status argument in not valid!");
        };
    }
}
