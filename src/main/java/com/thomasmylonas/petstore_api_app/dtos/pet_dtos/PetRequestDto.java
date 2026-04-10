package com.thomasmylonas.petstore_api_app.dtos.pet_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.petstore_api_app.dtos.category_dtos.CategoryRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagRequestDto;
import lombok.Builder;

import java.util.List;

@Builder
public record PetRequestDto(
        @JsonProperty(value = "name")
        String name,

        @JsonProperty(value = "status")
        String status,

        @JsonProperty(value = "category")
        CategoryRequestDto categoryRequestDto,

        @JsonProperty(value = "tags")
        List<TagRequestDto> tagRequestDtos,

        @JsonProperty(value = "photo_urls")
        List<PhotoUrlRequestDto> photoUrlRequestDtos
) {
}
