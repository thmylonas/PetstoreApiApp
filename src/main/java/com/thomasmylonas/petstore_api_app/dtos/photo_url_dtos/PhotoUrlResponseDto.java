package com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos;

import lombok.Builder;

@Builder
public record PhotoUrlResponseDto(
        Long id,
        String name
        //,Pet pet
) {
}
