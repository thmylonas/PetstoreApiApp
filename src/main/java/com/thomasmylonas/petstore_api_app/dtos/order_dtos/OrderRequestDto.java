package com.thomasmylonas.petstore_api_app.dtos.order_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record OrderRequestDto(
        @JsonProperty(value = "pet_id")
        long petId,

        @JsonProperty(value = "quantity")
        int quantity,

        @JsonProperty(value = "status")
        String status,

        @JsonProperty(value = "complete")
        boolean complete
) {
}
