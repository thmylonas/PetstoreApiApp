package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.photo_url_dtos.PhotoUrlResponseDto;
import com.thomasmylonas.petstore_api_app.dtos.tag_dtos.TagResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.entities.PhotoUrl;
import com.thomasmylonas.petstore_api_app.entities.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetMapper {

    private final TagMapper tagMapper;
    private final PhotoUrlMapper photoUrlMapper;

    public PetResponseDto fromPet(Pet pet) {

        List<TagResponseDto> tagResponseDtos = pet.getTags().stream()
                .map(tagMapper::fromTag)
                .toList();
        List<PhotoUrlResponseDto> photoUrlResponseDtos = pet.getPhotoUrls().stream()
                .map(photoUrlMapper::fromPhotoUrl)
                .toList();
        return PetResponseDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .status(pet.getStatus())
                .tagResponseDtos(tagResponseDtos)
                .photoUrlResponseDtos(photoUrlResponseDtos)
                .build();
    }

    public Pet toPet(PetRequestDto petRequestDto) {

        List<Tag> tags = petRequestDto.tagRequestDtos().stream()
                .map(tagMapper::toTag)
                .toList();
        List<PhotoUrl> photoUrls = petRequestDto.photoUrlRequestDtos().stream()
                .map(photoUrlMapper::toPhotoUrl)
                .toList();
        return Pet.builder()
                .name(petRequestDto.name())
                .status(petRequestDto.status())
                .tags(tags)
                .photoUrls(photoUrls)
                .build();
    }
}
