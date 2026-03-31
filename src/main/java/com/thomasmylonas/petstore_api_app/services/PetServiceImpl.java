package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.repositories.PetRepository;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service(value = "petService")
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet findPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(id));
    }

    @Override
    public List<Pet> findPetsByName(String name) {
        return petRepository.findByName(name)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The pet with name " + name + " is not found!"));
    }

    @Override
    public List<Pet> findPetsByStatus(String status) {
        return petRepository.findByStatus(PetStatus.fromValue(status))
                .orElseThrow(() -> new RequestedResourceNotFoundException("The pet with status " + status + " is not found!"));
    }

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
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
    public Pet updatePet(Long id, Pet pet) {

        /*if (id < 1) { //|| !UsefulUtils.isInteger(String.valueOf(id))
            //log.info("400 - Invalid ID supplied");
            throw new IllegalArgumentException("400 - Invalid ID supplied");
        }*/

        petRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(id));

        Pet updatedPet = mapPet(pet);

        return petRepository.save(updatedPet);
    }

    @Override
    public void deletePet(Long id) {

        /*if (id < 1) { //|| !UsefulUtils.isInteger(String.valueOf(id))
            //log.info("400 - Invalid ID supplied");
            throw new IllegalArgumentException("400 - Invalid ID supplied");
        }*/
        petRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(id));
        petRepository.deleteById(id);
    }

    private Pet mapPet(Pet pet) {

        Pet updatedPet = new Pet();

        if (StringUtils.hasLength(pet.getName())) {
            updatedPet.setName(pet.getName());
        }
        if (pet.getStatus() != null) {
            updatedPet.setStatus(pet.getStatus());
        }
        if (pet.getCategory() != null) {
            updatedPet.setCategory(pet.getCategory());
        }
        if (pet.getTags() != null) {
            updatedPet.setTags(pet.getTags());
        }
        if (pet.getPhotoUrls() != null) {
            updatedPet.setPhotoUrls(pet.getPhotoUrls());
        }
        return updatedPet;
    }
}
