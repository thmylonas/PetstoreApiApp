package com.thomasmylonas.petstore_api_app.api.dtos.tag_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TagResponseDto(
        @JsonProperty(value = "id")
        Long id,

        @JsonProperty(value = "name")
        String name
) {
}
