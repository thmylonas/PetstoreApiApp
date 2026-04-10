package com.thomasmylonas.petstore_api_app.dtos.pet_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.petstore_api_app.dtos.category_dtos.CategoryResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record PetResponseDto(
        @JsonProperty(value = "id")
        Long id,

        @JsonProperty(value = "name")
        String name,

        @JsonProperty(value = "status")
        String status,

        @JsonProperty(value = "category")
        CategoryResponseDto categoryResponseDto,

        @JsonProperty(value = "tags")
        List<TagResponseDto> tagResponseDtos,

        @JsonProperty(value = "photo_urls")
        List<PhotoUrlResponseDto> photoUrlResponseDtos
) {
}
