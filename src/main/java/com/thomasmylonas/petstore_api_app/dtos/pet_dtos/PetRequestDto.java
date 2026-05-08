package com.thomasmylonas.petstore_api_app.dtos.pet_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thomasmylonas.petstore_api_app.dtos.category_dtos.CategoryRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagRequestDto;
import com.thomasmylonas.petstore_api_app.validation.ValidatePetStatusType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record PetRequestDto(
        @NotBlank(message = "The 'name' must not be null and must contain at least one non-whitespace character")
        @JsonProperty(value = "name")
        String name,

        @ValidatePetStatusType
        @JsonProperty(value = "status")
        String status,

        @Valid
        @JsonProperty(value = "category")
        CategoryRequestDto categoryRequestDto,

        @NotEmpty(message = "The 'tagRequestDtos' must not be null or empty")
        @JsonProperty(value = "tags")
        List<@Valid TagRequestDto> tagRequestDtos,

        @NotEmpty(message = "The 'photoUrlRequestDtos' must not be null or empty")
        @JsonProperty(value = "photo_urls")
        List<@Valid PhotoUrlRequestDto> photoUrlRequestDtos
) {
}
