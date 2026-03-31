package com.thomasmylonas.petstore_api_app.dtos;

import com.thomasmylonas.petstore_api_app.entities.Category;
import com.thomasmylonas.petstore_api_app.entities.PhotoUrl;
import com.thomasmylonas.petstore_api_app.entities.Tag;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record PetResponseDto(
        Long id,
        String name,
        PetStatus status,
        Category category,
        List<Tag> tags,
        List<PhotoUrl> photoUrls
) {
}
