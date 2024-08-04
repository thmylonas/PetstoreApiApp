package com.thomasmylonas.petstore_api_web_app.service_layer.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
