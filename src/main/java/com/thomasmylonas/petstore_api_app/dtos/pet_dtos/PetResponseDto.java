package com.thomasmylonas.petstore_api_app.dtos.pet_dtos;

import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagResponseDto;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record PetResponseDto(
        Long id,
        String name,
        PetStatus status,
        //Category category,
        List<TagResponseDto> tagResponseDtos,
        List<PhotoUrlResponseDto> photoUrlResponseDtos
) {
}
