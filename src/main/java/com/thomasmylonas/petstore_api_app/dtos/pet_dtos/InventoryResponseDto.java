package com.thomasmylonas.petstore_api_app.dtos.pet_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import lombok.Builder;

@Builder
public record InventoryResponseDto(
        @JsonProperty(value = "status")
        PetStatus status,

        @JsonProperty(value = "quantities")
        Long quantities
) {
}
