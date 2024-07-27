package com.thomasmylonas.petstore_api_web_app.controllers;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.*;
import com.thomasmylonas.petstore_api_web_app.data_access_layer.repositories.PetRepository;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.PetModel;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.response_status_models.SuccessStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PetController {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private SuccessStatus successStatus;

    // http://localhost:8080/pet/{id}
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<PetModel> getById(@PathVariable Long id) {

        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
//            LOGGER.info("400 - Invalid ID supplied");
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
        }
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
//            LOGGER.info("The pet with id: " + id + ", is not found");
            throw new ResourceNotFoundException(id, "The pet with id: %d, is not found");
        }
//        LOGGER.info("The pet with id: " + id + ", is the\n" + pet);
        return new ResponseEntity<>(new PetModel(pet), HttpStatus.OK);
    }

    // http://localhost:8080/pet/findByStatus?status=sold
    @GetMapping(path = {"/findByStatus"})
    public ResponseEntity<List<PetModel>> findByStatus(
            @RequestParam(value = "status", defaultValue = "available") String status) {

        String[] statusArray = status.split(",");

        for (String s : statusArray) {
            if (!s.equals("available") && !s.equals("sold") && !s.equals("pending")) {
//                LOGGER.info("400 - Invalid status value");
                throw new InvalidInputSuppliedException("400 - Invalid status value");
            }
        }

        List<Pet> petList = petRepository.findAll();
        List<Pet> filteredPetList =
                petList.stream()
                        .filter(
                                pet -> {
                                    for (String s : statusArray) {
                                        if (pet.getStatus().getName().toLowerCase().equals(s)) {
                                            return true;
                                        }
                                    }
                                    return false;
                                }
                        )
                        .collect(Collectors.toList());
//        LOGGER.info("All pets filtered by status are: \n" + filteredPetList);

        List<PetModel> filteredPetModelList = new ArrayList<>();
        for (int i = 0; i < filteredPetList.size(); i++) {
            filteredPetModelList.add(new PetModel(filteredPetList.get(i)));
        }
        return new ResponseEntity<>(filteredPetModelList, HttpStatus.OK);
    }

    // http://localhost:8080/pet
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pet> save(@RequestBody PetModel petModel, UriComponentsBuilder ucb)
            throws HttpMessageNotReadableException, HttpMediaTypeNotSupportedException {

        Pet pet = new Pet(petModel);
        pet.setPhotoUrls(pet.getPhotoUrls());
        pet.setTags(pet.getTags());
        Pet petPersisted = petRepository.save(pet);

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
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
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
    public ResponseEntity<Pet> update(@RequestBody PetModel petModel) {

        Pet updatedPet;
        Long id = petModel.getId();
        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
//            LOGGER.info("400 - Invalid ID supplied");
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
        }

        Pet pet = new Pet(petModel);
        pet.setPhotoUrls(pet.getPhotoUrls());
        pet.setTags(pet.getTags());
        updatedPet = new Pet();//petRepository.update(pet);

        if (updatedPet == null) {
            throw new ResourceNotFoundException(0, "404 - Pet not found");
        }
//        LOGGER.info("The updated pet is:\n" + new PetModel(updatedPet));
        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    // http://localhost:8080/pet/{id}
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<SuccessStatus> delete(@PathVariable Long id) {

        if (id < 1 //|| !UsefulUtils.isInteger(String.valueOf(id))
        ) {
//            LOGGER.info("400 - Invalid ID supplied");
            throw new InvalidInputSuppliedException("400 - Invalid ID supplied");
        }

        try {
            petRepository.deleteById(id);
            String message = String.format("The pet with the id: " + id + ", is deleted", id);
//            setResponseStatus(successStatus, null, HttpStatus.OK, message);
//            LOGGER.info("The pet with the id: " + id + ", is deleted");
        } catch (ResourceNotFoundException e) {
//            LOGGER.info("The pet with id: " + id + ", is not found");
            throw new ResourceNotFoundException(e.getResourceId(), e.getMyMessage());
        }
        return new ResponseEntity<>(successStatus, HttpStatus.OK);
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
