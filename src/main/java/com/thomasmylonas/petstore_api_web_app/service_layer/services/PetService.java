package com.thomasmylonas.petstore_api_web_app.service_layer.services;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.PetPage;

import java.util.List;

public interface PetService {

    /**
     * The method fetches the Pet, by the given ID
     *
     * @param id The ID of the Pet to fetch
     * @return The Pet we need to fetch
     * //     * @throws InvalidInputSuppliedException If the input is invalid (e.g. negative id)
     * //     * @throws ResourceNotFoundException     If the resource is not found
     */
    Pet fetchPetById(Long id);

    List<Pet> fetchPetsByName(String name);

    List<Pet> fetchPetsByStatus(String status);

    /**
     * It returns the list of all Pets
     *
     * @return The list of all Pets
     */
    List<Pet> fetchAllPets();

    PetPage fetchPageOfPets();

    /**
     * It saves a new Entity and its dependent Entities, and the Exceptions thrown are handled
     * in the "ExceptionsHandlerController"
     *
     * @param pet The pet to save
     * @return The saved pet
     * //     * @throws HttpMessageNotReadableException    If RequestBody is not like "TModel", but like other models
     * //     * @throws HttpMediaTypeNotSupportedException Thrown runtime, if RequestBody is null or not valid JSON
     */
    Pet savePet(Pet pet);

    List<Pet> savePetsInBatch(List<Pet> pets);

    /**
     * @param pet The new Pet to update from
     * @param id  The ID of the Pet to update
     * @return The ResponseEntity of the request
     * //     * @throws InvalidInputSuppliedException If the input is invalid (eg. negative id)
     * //     * @throws ResourceNotFoundException     If the resource to be updated is not found
     * //     * @throws Exception                     405: Validation exception // Not implemented
     */
    Pet updatePet(Long id, Pet pet);

    /**
     * It deletes the Pet, with the given in the parameter id
     *
     * @param id The ID of the Pet to delete
     *           //     * @throws InvalidInputSuppliedException If the input is invalid (eg. negative id)
     *           //     * @throws ResourceNotFoundException     If the resource to be deleted is not found
     */
    void deletePet(Long id);
}
