package com.thomasmylonas.petstore_api_app.dtos.category_dtos;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponseDto(
        Integer id,
        String name,
        List<PetResponseDto> petResponseDtos
) {
}
