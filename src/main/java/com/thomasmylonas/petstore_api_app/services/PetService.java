package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;

import java.util.List;

public interface PetService {

    /**
     * The method finds the Pet, by the given ID
     *
     * @param id The ID of the Pet to find
     * @return The Pet we need to find
     * @throws RequestedResourceNotFoundException If the resource is not found
     */
    PetRequestDto findPetById(Long id);

    List<PetResponseDto> findPetsByName(String name);

    List<PetResponseDto> findPetsByStatus(String status);

    /**
     * It returns the list of all Pets
     *
     * @return The list of all Pets
     */
    List<PetResponseDto> findAllPets();

    /**
     * It saves a new Entity and its dependent Entities, and the Exceptions thrown are handled
     * in the "ExceptionsHandlerController"
     *
     * @param petRequestDto The pet to save
     * @return The saved pet
     */
    PetResponseDto savePet(PetRequestDto petRequestDto);

    List<PetResponseDto> saveAllPets(List<PetRequestDto> petRequestDtos);

    /**
     * @param pet The new Pet to update from
     * @param id  The ID of the Pet to update
     * @return The ResponseEntity of the request
     * @throws RequestedResourceNotFoundException If the resource to be updated is not found
     * @throws Exception                          405: Validation exception // Not implemented
     */
    Pet updatePet(Long id, Pet pet);

    /**
     * It deletes the Pet, with the given in the parameter id
     *
     * @param id The ID of the Pet to delete
     * @throws RequestedResourceNotFoundException If the resource to be deleted is not found
     */
    void deletePet(Long id);
}
