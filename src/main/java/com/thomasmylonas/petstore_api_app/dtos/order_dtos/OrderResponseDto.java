package com.thomasmylonas.petstore_api_app.dtos.order_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponseDto(
        @JsonProperty(value = "id")
        Long id,

        @JsonProperty(value = "pet_id")
        long petId,

        @JsonProperty(value = "quantity")
        int quantity,

        @JsonProperty(value = "ship_date")
        LocalDateTime shipDate,

        @JsonProperty(value = "status")
        String status,

        @JsonProperty(value = "complete")
        boolean complete
) {
}
