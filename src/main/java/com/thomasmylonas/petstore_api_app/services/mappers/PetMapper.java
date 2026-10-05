package com.thomasmylonas.petstore_api_app.services.mappers;

import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.photo_url_dtos.PhotoUrlResponseDto;
import com.thomasmylonas.petstore_api_app.api.dtos.tag_dtos.TagResponseDto;
//import com.thomasmylonas.petstore_api_app.entities.Category;
import com.thomasmylonas.petstore_api_app.api.entities.Pet;
import com.thomasmylonas.petstore_api_app.api.entities.PhotoUrl;
import com.thomasmylonas.petstore_api_app.api.entities.Tag;
import com.thomasmylonas.petstore_api_app.api.enums.PetStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetMapper {

    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;
    private final PhotoUrlMapper photoUrlMapper;

    public Pet toPet(PetRequestDto petRequestDto) {

        List<Tag> tags = petRequestDto.tagRequestDtos().stream()
                .map(tagMapper::toTag)
                .toList();
        List<PhotoUrl> photoUrls = petRequestDto.photoUrlRequestDtos().stream()
                .map(photoUrlMapper::toPhotoUrl)
                .toList();
        //Category category = categoryMapper.toCategory(petRequestDto.categoryRequestDto());

        Pet pet = Pet.builder()
                .name(petRequestDto.name())
                .status(PetStatus.valueOfPetStatus(petRequestDto.status().toUpperCase()))
                //.category(category)
                .tags(new ArrayList<>())
                .photoUrls(new ArrayList<>())
                .build();

        //category.addPet(pet); // It seems this method is not needed (I do not know why)

        tags.forEach(pet::addTag);
        photoUrls.forEach(pet::addPhotoUrl);
        return pet;
    }

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
                .status(pet.getStatus().getValue())
                .categoryResponseDto(categoryMapper.fromCategory(pet.getCategory()))
                .tagResponseDtos(tagResponseDtos)
                .photoUrlResponseDtos(photoUrlResponseDtos)
                .build();
    }
}
