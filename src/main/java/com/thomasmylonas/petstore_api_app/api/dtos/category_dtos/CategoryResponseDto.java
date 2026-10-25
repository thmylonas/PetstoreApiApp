package com.thomasmylonas.petstore_api_app.api.dtos.category_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CategoryResponseDto(
        @JsonProperty(value = "id")
        Integer id,

        @JsonProperty(value = "name")
        String name
) {
}
