package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;

import java.util.List;

public interface PetService {

    PetResponseDto findPetById(Long id) throws RequestedResourceNotFoundException;

    List<PetResponseDto> findPetsByName(String name);

    List<PetResponseDto> findPetsByStatus(String status);

    List<PetResponseDto> findAllPets();

    List<PetResponseDto> findAllPetsSorted(String sortBy, String sortDirection);

    PetResponseDto savePet(PetRequestDto petRequestDto);

    List<PetResponseDto> saveAllPets(List<PetRequestDto> petRequestDtos);

    PetResponseDto updatePet(PetRequestDto petRequestDto, Long id) throws RequestedResourceNotFoundException;

    void deletePetById(Long id) throws RequestedResourceNotFoundException;

    PetResponseDto updatePetWithForm(Long id, String name, String status);
}
