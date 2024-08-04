package com.thomasmylonas.petstore_api_web_app.service_layer.services;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.data_access_layer.repositories.PetRepository;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.PetPage;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.enums.PetStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "petService")
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet fetchPetById(Long id) {
        /*if (id < 1) { //|| !UsefulUtils.isInteger(String.valueOf(id))
            //LOGGER.info("400 - Invalid ID supplied");
            throw new IllegalArgumentException("400 - Invalid ID supplied");
        }*/
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public List<Pet> fetchPetsByName(String name) {
        return petRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("The pet with name " + name + " is not found!"));
    }

    @Override
    public List<Pet> fetchPetsByStatus(String status) {
        return petRepository.findByStatus(PetStatusEnum.fromValue(status))
                .orElseThrow(() -> new ResourceNotFoundException("The pet with status " + status + " is not found!"));
    }

    @Override
    public List<Pet> fetchAllPets() {
        return petRepository.findAll();
    }

    @Override
    public PetPage fetchPageOfPets() {
        return null;
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> savePetsInBatch(List<Pet> pets) {
        return petRepository.saveAll(pets);
    }

    @Override
    public Pet update(Pet newPet, Long id) {
        return null;
    }

    @Override
    public void deletePet(Long id) {

        /*if (id < 1) { //|| !UsefulUtils.isInteger(String.valueOf(id))
            //LOGGER.info("400 - Invalid ID supplied");
            throw new IllegalArgumentException("400 - Invalid ID supplied");
        }*/
        petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        petRepository.deleteById(id);
    }
}
