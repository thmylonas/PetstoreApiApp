package com.thomasmylonas.petstore_api_app.services;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.repositories.PetRepository;
import com.thomasmylonas.petstore_api_app.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.services.mappers.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service(value = "petService")
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Override
    public PetResponseDto findPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(Pet.class.getSimpleName(), id));
        return petMapper.fromPet(pet);
    }

    @Override
    public List<PetResponseDto> findPetsByName(String name) {
        List<Pet> pets = petRepository.findByName(name)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The pet with name " + name + " is not found!"));
        return pets.stream().map(petMapper::fromPet).toList();
    }

    @Override
    public List<PetResponseDto> findPetsByStatus(String status) {
        List<Pet> pets = petRepository.findByStatus(PetStatus.fromValue(status))
                .orElseThrow(() -> new RequestedResourceNotFoundException("The pet with status " + status + " is not found!"));
        return pets.stream().map(petMapper::fromPet).toList();
    }

    @Override
    public List<PetResponseDto> findAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream().map(petMapper::fromPet).toList();
    }

    @Override
    public List<PetResponseDto> findAllPetsSorted(String sortBy, String sortDirection) {
        List<Pet> pets = petRepository.findAll(sort(sortBy, sortDirection));
        return pets.stream().map(petMapper::fromPet).toList();
    }

    @Override
    public PetResponseDto savePet(PetRequestDto petRequestDto) {
        Pet pet = petMapper.toPet(petRequestDto);
        Pet savedPet = petRepository.save(pet);
        return petMapper.fromPet(savedPet);
    }

    @Override
    public List<PetResponseDto> saveAllPets(List<PetRequestDto> petRequestDtos) {
        List<Pet> pets = petRequestDtos.stream().map(petMapper::toPet).toList();
        List<Pet> savedPets = petRepository.saveAll(pets);
        return savedPets.stream().map(petMapper::fromPet).toList();
    }

    @Override
    public PetResponseDto updatePet(Long id, PetRequestDto petRequestDto) {

        Pet petToUpdate = petRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(Pet.class.getSimpleName(), id));
        Pet pet = petMapper.toPet(petRequestDto);

        if (StringUtils.hasLength(pet.getName())) {
            petToUpdate.setName(pet.getName());
        }
        if (Objects.nonNull(pet.getStatus())) {
            petToUpdate.setStatus(pet.getStatus());
        }
        if (Objects.nonNull(pet.getCategory())) {
            petToUpdate.setCategory(pet.getCategory());
        }
        if (Objects.nonNull(pet.getTags())) {
            petToUpdate.setTags(pet.getTags());
        }
        if (Objects.nonNull(pet.getPhotoUrls())) {
            petToUpdate.setPhotoUrls(pet.getPhotoUrls());
        }
        Pet updatedPet = petRepository.save(petToUpdate);
        return petMapper.fromPet(updatedPet);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    private static Sort sort(String sortBy, String sortDirection) {
        return "desc".equalsIgnoreCase(sortDirection) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    }
}
