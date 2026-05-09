package com.thomasmylonas.petstore_api_app.dtos.order_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.petstore_api_app.validation.ValidateBooleanType;
import com.thomasmylonas.petstore_api_app.validation.ValidateOrderStatusType;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record OrderRequestDto(
        @PositiveOrZero(message = "The 'petId' must be a positive number or 0")
        @JsonProperty(value = "pet_id")
        long petId,

        @PositiveOrZero(message = "The 'quantity' must be a positive number or 0")
        @JsonProperty(value = "quantity")
        int quantity,

        @ValidateOrderStatusType
        @JsonProperty(value = "status")
        String status,

        @ValidateBooleanType
        @JsonProperty(value = "complete")
        boolean complete
) {
}
