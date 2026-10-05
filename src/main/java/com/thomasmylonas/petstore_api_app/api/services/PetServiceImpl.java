package com.thomasmylonas.petstore_api_app.api.services;

import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.api.entities.Category;
import com.thomasmylonas.petstore_api_app.api.entities.Pet;
import com.thomasmylonas.petstore_api_app.api.repositories.CategoryRepository;
import com.thomasmylonas.petstore_api_app.api.repositories.PetRepository;
import com.thomasmylonas.petstore_api_app.api.exceptions.RequestedResourceNotFoundException;
import com.thomasmylonas.petstore_api_app.api.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.api.services.mappers.CategoryMapper;
import com.thomasmylonas.petstore_api_app.api.services.mappers.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service(value = "petService")
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final CategoryRepository categoryRepository;
    private final PetMapper petMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public PetResponseDto findPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(Pet.class.getSimpleName(), id));
        return petMapper.fromPet(pet);
    }

    @Override
    public List<PetResponseDto> findPetsByName(String name) {
        List<Pet> pets = petRepository.findByName(name)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The pets with name " + name + " are not found!"));
        return pets.stream().map(petMapper::fromPet).toList();
    }

    @Override
    public List<PetResponseDto> findPetsByStatus(String status) {

        String[] statuses = status.split(",");

        boolean notAllStatuses = Arrays.stream(statuses).anyMatch(s -> !PetStatus.isPetStatus(s));
        if (notAllStatuses) {
            throw new IllegalArgumentException("400 - Invalid status value");
        }

        List<Pet> petsByStatus = new ArrayList<>();
        for (String s : statuses) {
            List<Pet> pets = petRepository.findByStatus(PetStatus.valueOfPetStatus(s))
                    .orElseThrow(() -> new RequestedResourceNotFoundException("The pets with status " + status + " are not found!"));
            petsByStatus.addAll(pets);
        }
        return petsByStatus.stream().map(petMapper::fromPet).toList();
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

        // Retrieve data from input parameters
        Pet pet = petMapper.toPet(petRequestDto);
        Category category = categoryMapper.toCategory(petRequestDto.categoryRequestDto());
        String categoryName = category.getName();

        // Query if Category (Parent) exists in DB
        List<Category> existingCategoriesByName = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RequestedResourceNotFoundException("The Category with name " + categoryName + " is not found!"));
        if (existingCategoriesByName.isEmpty()) {
            // Persist Category (Parent) only if it does not exist in DB, and set Pet (Child) to persist
            Category savedCategory = categoryRepository.save(category);
            pet.setCategory(savedCategory);
        } else {
            // Set Pet (Child) to persist
            pet.setCategory(existingCategoriesByName.getFirst());
        }

        // Persist Pet (Child)
        Pet savedPet = petRepository.save(pet);

        // Return the response
        return petMapper.fromPet(savedPet);
    }

    @Override
    public List<PetResponseDto> saveAllPets(List<PetRequestDto> petRequestDtos) {
        return petRequestDtos.stream().map(this::savePet).toList();
    }

    @Override
    public PetResponseDto updatePet(PetRequestDto petRequestDto, Long id) {

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
            petToUpdate.setCategory(pet.getCategory()); // TODO: What id the category does not exit?
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

    @Override
    public PetResponseDto updatePetWithForm(Long id, String name, String status) {

        Pet petToUpdate = petRepository.findById(id)
                .orElseThrow(() -> new RequestedResourceNotFoundException(Pet.class.getSimpleName(), id));

        if (StringUtils.hasLength(name)) {
            petToUpdate.setName(name);
        }
        if (StringUtils.hasLength(status)) {
            petToUpdate.setStatus(PetStatus.valueOfPetStatus(status));
        }
        Pet updatedPet = petRepository.save(petToUpdate);
        return petMapper.fromPet(updatedPet);
    }

    private static Sort sort(String sortBy, String sortDirection) {
        return "desc".equalsIgnoreCase(sortDirection) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    }
}
