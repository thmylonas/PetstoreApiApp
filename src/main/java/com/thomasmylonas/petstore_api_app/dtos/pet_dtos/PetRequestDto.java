package com.thomasmylonas.petstore_api_app.dtos.pet_dtos;

import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagRequestDto;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record PetRequestDto(
        String name,
        PetStatus status,
        //Category category,
        List<TagRequestDto> tagRequestDtos,
        List<PhotoUrlRequestDto> photoUrlRequestDtos
) {
}
