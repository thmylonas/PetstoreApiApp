package com.thomasmylonas.petstore_api_web_app._;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.data_access_layer.repositories.PetRepository;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.response_status_models.ErrorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * This is an alternative Controller, written as tutorial. It seems to be all wrong
 */
@RestController
@RequestMapping(value = "/pet")
public class _alt_PetController {

    private final PetRepository petRepository;

    @Autowired
    public _alt_PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /*@GetMapping(value = "/findByStatus") // Todo: Where the status comes from?
    public List<Pet> getAllPetsByStatus(EnumStatus[] status) {
        return petRepository.findAllPetsByStatus(status);
    }*/

    @PostMapping
    public int postPet(@RequestBody Pet pet) {
        petRepository.save(pet);
        return 405;
    }

    @GetMapping(value = "/{petId}")
    public ResponseEntity<Pet> petById1(@PathVariable("petId") long id) {
        Pet pet = petRepository.findById(id).orElse(null);
        HttpStatus status = pet != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Pet>(pet, status);
    }

    @GetMapping(value = "/{petId}")
    public ResponseEntity<?> petById2(@PathVariable("petId") long id) {

        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            ErrorStatus error = new ErrorStatus(); // new ErrorStatus(404, "Pet[" + id + "] not found", "");
            return new ResponseEntity<ErrorStatus>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pet>(pet, HttpStatus.OK); // HTTP Status Code 200
    }

    @GetMapping(value = "/{petId}")
    public ResponseEntity<Pet> petById3(@PathVariable("petId") long id) {

        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new ResourceNotFoundException(id, "The resource not found!");
        }
        return new ResponseEntity<Pet>(pet, HttpStatus.OK); // HTTP Status Code 200
    }

    @GetMapping(value = "/{petId}")
    public Pet petById4(@PathVariable("petId") long id) {

        Pet pet = petRepository.findById(id).orElse(null);
        if (pet == null) {
            throw new ResourceNotFoundException(id, "The resource not found!");
        }
        return pet;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // HTTP Status Code 201
    public Pet savePet1(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    @PostMapping
    public ResponseEntity<Pet> savePet2(@RequestBody Pet pet) {

        Pet petResponse = petRepository.save(pet);

        HttpHeaders headers = new HttpHeaders();
        // The following URL is hardcoded
        URI locationUri = URI.create("http://localhost:8080/app_name/pet/" + petResponse.getId());
        headers.setLocation(locationUri); // Set the  location header

        return new ResponseEntity<Pet>(petResponse, headers, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Pet> savePet3(@RequestBody Pet pet, UriComponentsBuilder ucb) {

        // Given a UriComponentsBuilder
        Pet petResponse = petRepository.save(pet);

        HttpHeaders headers = new HttpHeaders();
        // Calculate  the  location  URI
        URI locationUri =
                ucb.path("/pet/").
                        path(String.valueOf(petResponse.getId())).
                        build().toUri();
        headers.setLocation(locationUri); // Set the  location header

        return new ResponseEntity<Pet>(petResponse, headers, HttpStatus.CREATED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorStatus> resourceNotFound1(ResourceNotFoundException e) {
        long id = e.getResourceId();
        ErrorStatus error = new ErrorStatus(); // new ErrorStatus(404, "Pet[" + id + "] not found", "");
        return new ResponseEntity<ErrorStatus>(error, HttpStatus.NOT_FOUND); // HTTP Status Code 404
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // HTTP Status Code 404
    public ErrorStatus resourceNotFound2(ResourceNotFoundException e) {
        long id = e.getResourceId();
        return new ErrorStatus(); // new ErrorStatus(404, "Pet[" + id + "] not found", "");
    }
}
// HTTP Status Code 409
