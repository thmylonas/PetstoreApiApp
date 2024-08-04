package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.enums.PetStatusEnum;
import com.thomasmylonas.petstore_api_web_app.service_layer.services.PetService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/pet")
@RequiredArgsConstructor
public class PetController {

    private final static Logger LOGGER = LoggerFactory.getLogger(Class.class.getSimpleName());

    private final PetService petService;

    // http://localhost:8080/pet/{petId}
    @GetMapping(path = {"/{petId}"})
    public ResponseEntity<Pet> fetchPetById(@PathVariable(value = "petId") Long id) {
        Pet petFetched = petService.fetchPetById(id);
        LOGGER.info("The pet with ID {}, is the {}", id, petFetched);
        return new ResponseEntity<>(petFetched, HttpStatus.OK);
    }

    /**
     * http://localhost:8080/pet/findByStatus?status=sold
     * It returns the list of Pets filtered by status
     *
     * @param status status: available, pending, sold, and all the combinations
     * @return The petList filtered by status
     * @throws IllegalArgumentException If the input is invalid (e.g. status is not as defined)
     */
    @GetMapping(path = {"/findByStatus"})
    public ResponseEntity<List<Pet>> fetchPetsByStatus(@RequestParam(value = "status", defaultValue = "available") String status) {

        String[] statuses = status.split(",");

        for (String s : statuses) {
            if (!PetStatusEnum.isPetStatus(s)) {
                LOGGER.info("400 - Invalid status value");
                throw new IllegalArgumentException("400 - Invalid status value");
            }
        }

        List<Pet> petsByStatus = new ArrayList<>();
        for (String s : statuses) {
            petsByStatus.addAll(petService.fetchPetsByStatus(s));
        }
        LOGGER.info("All pets filtered by status {} are: {}", status, petsByStatus);
        return ResponseEntity.ok(petsByStatus);
    }

    // http://localhost:8080/pet/all-pets
    @GetMapping(path = {"/all-pets"})
    public ResponseEntity<List<Pet>> fetchAllPets() {
        List<Pet> pets = petService.fetchAllPets();
        LOGGER.info("All pets are: {}", pets);
        return ResponseEntity.ok(pets);
    }

    // http://localhost:8080/pet
    @PostMapping
    public ResponseEntity<Pet> savePet(@RequestBody Pet pet, UriComponentsBuilder ucb) {

        Pet petPersisted = petService.savePet(pet);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/pet")
                .path(String.valueOf(petPersisted.getId()))
                .build().toUri();
        headers.setLocation(locationUri);

        LOGGER.info("The new pet: {} is persisted, with ID: {}", petPersisted, petPersisted.getId());

        return ResponseEntity.created(locationUri).headers(headers).body(petPersisted);
    }

    // http://localhost:8080/pet/{petId}
    @PutMapping(path = {"/{petId}"})
    public ResponseEntity<Pet> updatePet(@PathVariable(value = "petId") Long id, @RequestBody Pet pet) {
        Pet updatedPet = petService.updatePet(id, pet);
        LOGGER.info("The updated pet is: {}", updatedPet);
        return ResponseEntity.ok(updatedPet);
    }

    // http://localhost:8080/pet/{petId}
    @DeleteMapping(path = {"/{petId}"})
    public ResponseEntity<String> delete(@PathVariable(value = "petId") Long id) {
        petService.deletePet(id);
        LOGGER.info("The pet with ID {}, is deleted", id);
        return ResponseEntity.ok("The pet with ID " + id + ", is deleted");
    }

    /**
     * TODO: Implement this method
     */
    @PostMapping(path = {"/{id}"},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Pet> updateWithForm(@PathVariable int id,
                                              @RequestParam MultiValueMap<String, String> paramMap) throws Exception {

        /*if (id < 1) { //|| !UsefulUtils.isInteger(String.valueOf(id))
            //LOGGER.info("400 - Invalid ID supplied");
            throw new IllegalArgumentException("400 - Invalid ID supplied");
        }*/

//        Pet pet = new Pet(petModel);
//        pet.setPhotoUrls(pet.getPhotoUrls());
//        pet.setTags(pet.getTags());
//        Pet petPersisted = petRepository.save(pet);

//        LOGGER.info("The new pet:\n" + petPersisted + "\nis added, with the id: " + petPersisted.getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
