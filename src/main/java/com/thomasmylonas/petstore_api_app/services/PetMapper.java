package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {

    public PetResponseDto fromPet(Pet pet) {
        return PetResponseDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .status(pet.getStatus())
                .category(pet.getCategory())
                .tags(pet.getTags())
                .photoUrls(pet.getPhotoUrls())
                .build();
    }

    public Pet toPet(PetRequestDto petRequestDto) {
        return Pet.builder()
                .name(petRequestDto.name())
                .status(petRequestDto.status())
                .category(petRequestDto.category())
                .tags(petRequestDto.tags())
                .photoUrls(petRequestDto.photoUrls())
                .build();
    }
}
