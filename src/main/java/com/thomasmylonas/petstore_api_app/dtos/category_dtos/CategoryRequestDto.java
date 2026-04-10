package com.thomasmylonas.petstore_api_app.dtos.category_dtos;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetRequestDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CategoryRequestDto(
        String name,
        List<PetRequestDto> petRequestDtos
) {
}
