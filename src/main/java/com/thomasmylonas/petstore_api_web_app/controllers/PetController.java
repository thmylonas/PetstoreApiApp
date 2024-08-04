package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.service_layer.services.PetService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pet")
@RequiredArgsConstructor
public class PetController {

    private final static Logger LOGGER = LoggerFactory.getLogger(Class.class.getSimpleName());

    private final PetService petService;

    // http://localhost:8080/pet/{petId}
    @GetMapping(path = {"/{petId}"})
    public ResponseEntity<Pet> getById(@PathVariable(value = "petId") Long id) {
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
     * //     * @throws IllegalArgumentException If the input is invalid (eg. status is not as defined)
     */
    @GetMapping(path = {"/findByStatus"})
    public ResponseEntity<List<Pet>> findByStatus(@RequestParam(value = "status", defaultValue = "available") String status) {

        String[] statusArray = status.split(",");

        for (String s : statusArray) {
            if (!s.equals("available") && !s.equals("sold") && !s.equals("pending")) {
//                LOGGER.info("400 - Invalid status value");
                throw new IllegalArgumentException("400 - Invalid status value");
            }
        }

        List<Pet> petList = petService.fetchPetsByStatus(null);
        List<Pet> filteredPetList =
                petList.stream()
                        .filter(
                                pet -> {
                                    for (String s : statusArray) {
                                        if (pet.getStatus().getValue().toLowerCase().equals(s)) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        )
                        .collect(Collectors.toList());
//        LOGGER.info("All pets filtered by status are: \n" + filteredPetList);

        List<Pet> filteredPetModelList = new ArrayList<>();
        for (int i = 0; i < filteredPetList.size(); i++) {
//            filteredPetModelList.add(new Pet(filteredPetList.get(i)));
        }
        return new ResponseEntity<>(filteredPetModelList, HttpStatus.OK);
    }

    // http://localhost:8080/pet
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pet> save(@RequestBody Pet pet, UriComponentsBuilder ucb)
            throws HttpMessageNotReadableException, HttpMediaTypeNotSupportedException {

//        pet.setPhotoUrls(pet.getPhotoUrls());
//        pet.setTags(pet.getTags());
        Pet petPersisted = petService.savePet(pet);

        HttpHeaders headers = new HttpHeaders();
//        URI locationUri = URI.create("http://localhost:8080/pet/" + petPersisted.getId());
        URI locationUri = ucb.path("/pet")
                .path(String.valueOf(petPersisted.getId()))
                .build().toUri();
        headers.setLocation(locationUri);

//        LOGGER.info("The new pet:\n" + petPersisted + "\nis added, with the id: " + petPersisted.getId());
        return new ResponseEntity<>(petPersisted, headers, HttpStatus.CREATED);
    }

    @PostMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pet> updateWithForm(@PathVariable int id) {

        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
//            LOGGER.info("400 - Invalid ID supplied");
            throw new IllegalArgumentException("400 - Invalid ID supplied");
        }
//        Pet pet = new Pet(petModel);
//        pet.setPhotoUrls(pet.getPhotoUrls());
//        pet.setTags(pet.getTags());
//        Pet petPersisted = petRepository.save(pet);

//        LOGGER.info("The new pet:\n" + petPersisted + "\nis added, with the id: " + petPersisted.getId());
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    // http://localhost:8080/pet
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pet> update(@RequestBody Pet pet) {

        Pet updatedPet;
        Long id = pet.getId();
        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
//            LOGGER.info("400 - Invalid ID supplied");
            throw new IllegalArgumentException("400 - Invalid ID supplied");
        }

//        pet.setPhotoUrls(pet.getPhotoUrls());
//        pet.setTags(pet.getTags());
        updatedPet = new Pet();//petRepository.update(pet);

        if (updatedPet == null) {
            throw new ResourceNotFoundException(0L);
        }
//        LOGGER.info("The updated pet is:\n" + new PetModel(updatedPet));
        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    // http://localhost:8080/pet/{petId}
    @DeleteMapping(path = {"/{petId}"})
    public ResponseEntity<String> delete(@PathVariable(value = "petId") Long id) {
        petService.deletePet(id);
        LOGGER.info("The pet with ID {}, is deleted", id);
        return ResponseEntity.ok("The pet with ID " + id + ", is deleted");
    }

    /*@Deprecated
    @GetMapping(path = {"/{id}"})
    public Pet getByIdSimpleVersion(@PathVariable Long id) {
        Pet pet = petRepository.getOne(id);
        LOGGER.info("The pet with id: " + id + ", is the\n" + pet);
        return pet;
    }
    @GetMapping(path = {"/all"})
    public List<Pet> getAll() {
        List<Pet> petList = petRepository.findAll();
        LOGGER.info("All pets are: \n" + petList);
        return petList;
    }*/
}
