package com.thomasmylonas.petstore_api_app.api.dtos.photo_url_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PhotoUrlResponseDto(
        @JsonProperty(value = "id")
        Long id,

        @JsonProperty(value = "name")
        String name
) {
}
