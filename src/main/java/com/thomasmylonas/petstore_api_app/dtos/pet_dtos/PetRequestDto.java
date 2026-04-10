package com.thomasmylonas.petstore_api_app.dtos.pet_dtos;

import com.thomasmylonas.petstore_api_app.dtos.category_dtos.CategoryRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagRequestDto;
import lombok.Builder;

import java.util.List;

@Builder
public record PetRequestDto(
        String name,
        String status,
        CategoryRequestDto categoryRequestDto,
        List<TagRequestDto> tagRequestDtos,
        List<PhotoUrlRequestDto> photoUrlRequestDtos
) {
}
