package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;

import java.util.List;

public interface PetService {

    PetResponseDto findPetById(Long id) throws RequestedResourceNotFoundException;

    List<PetResponseDto> findPetsByName(String name);

    List<PetResponseDto> findPetsByStatus(String status);

    List<PetResponseDto> findAllPets();

    PetResponseDto savePet(PetRequestDto petRequestDto);

    List<PetResponseDto> saveAllPets(List<PetRequestDto> petRequestDtos);

    PetResponseDto updatePet(Long id, PetRequestDto petRequestDto) throws RequestedResourceNotFoundException;

    void deletePetById(Long id) throws RequestedResourceNotFoundException;
}
