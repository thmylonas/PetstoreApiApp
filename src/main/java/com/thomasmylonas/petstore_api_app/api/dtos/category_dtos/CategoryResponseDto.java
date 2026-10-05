package com.thomasmylonas.petstore_api_app.api.dtos.category_dtos;

import lombok.Builder;

@Builder
public record CategoryResponseDto(Integer id, String name) {
}
