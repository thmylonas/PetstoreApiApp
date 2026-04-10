package com.thomasmylonas.petstore_api_app.dtos.tag_dtos;

import lombok.Builder;

@Builder
public record TagResponseDto(Long id, String name) {
}
