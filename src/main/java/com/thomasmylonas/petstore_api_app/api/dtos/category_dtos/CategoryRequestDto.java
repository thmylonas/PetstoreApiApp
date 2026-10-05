package com.thomasmylonas.petstore_api_app.api.dtos.category_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
        @NotBlank(message = "The 'name' must not be null and must contain at least one non-whitespace character")
        @JsonProperty(value = "name")
        String name
) {
}
