package com.thomasmylonas.petstore_api_app.dtos.category_dtos;

import lombok.Builder;

@Builder
public record CategoryRequestDto(String name) {
}
