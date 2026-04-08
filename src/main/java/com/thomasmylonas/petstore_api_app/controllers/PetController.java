package com.thomasmylonas.petstore_api_app.controllers;

import com.thomasmylonas.petstore_api_app.dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.PetResponseDto;
//import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseSuccess;
import com.thomasmylonas.petstore_api_app.services.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/pets")
@RequiredArgsConstructor
@Slf4j
public class PetController {

    private static final String REQUEST_MAPPING = "/api/v1/pets";

    private final PetService petService;
    private final ResponseBuilder responseBuilder;

    @GetMapping(path = {"/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findPetById(@PathVariable(value = "id") Long id) { // "http://localhost:8080/api/v1/pets/{id}"
        final String message = "Success: The Pet with ID " + id + " is found!";
        PetResponseDto petResponseDto = petService.findPetById(id);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("pet_response", petResponseDto));
    }

//    /**
//     * "http://localhost:8080/api/v1/pets/findByStatus?status=sold"
//     * It returns the list of Pets filtered by status
//     *
//     * @param status status: available, pending, sold, and all the combinations
//     * @return The petList filtered by status
//     * @throws IllegalArgumentException If the input is invalid (e.g. status is not as defined)
//     */
//    @GetMapping(path = {"/findByStatus"})
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseEntity<ResponseSuccess> findPetsByStatus(@RequestParam(value = "status", defaultValue = "available") String status) {
//
//        String[] statuses = status.split(",");
//
//        for (String s : statuses) {
//            if (!PetStatus.isPetStatus(s)) {
//                log.info("400 - Invalid status value");
//                throw new IllegalArgumentException("400 - Invalid status value");
//            }
//        }
//
//        List<Pet> petsByStatus = new ArrayList<>();
//        for (String s : statuses) {
//            petsByStatus.addAll(petService.findPetsByStatus(s));
//        }
//        log.info("All pets filtered by status {} are: {}", status, petsByStatus);
//        return ResponseEntity.ok(petsByStatus);
//    }
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllPets() { // "http://localhost:8080/api/v1/pets"
        final String message = "Success: The Pets are found!";
        List<PetResponseDto> petResponseDtos = petService.findAllPets();
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("pets_response", petResponseDtos));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> savePet(@RequestBody PetRequestDto petRequestDto) { // "http://localhost:8080/api/v1/pets"

        final String message = "Created: The Pet has been created successfully!";
        PetResponseDto savedPetResponseDto = petService.savePet(petRequestDto);
        String savedPetUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(savedPetResponseDto.id())
                .toUriString();
        return responseBuilder.buildResponse(HttpStatus.CREATED, message, savedPetUri, Map.of("saved_pet_response", savedPetResponseDto));
    }

    @PostMapping("/all")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllPets(@RequestBody List<PetRequestDto> petRequestDtos) { // "http://localhost:8080/api/v1/pets/all"
        final String message = "Created: The Pets have been created successfully!";
        List<PetResponseDto> savedPetResponseDtos = petService.saveAllPets(petRequestDtos);
        return responseBuilder.buildResponse(HttpStatus.CREATED, message, Map.of("saved_pets_response", savedPetResponseDtos));
    }

//    @PutMapping(path = {"/{id}"})
//    public ResponseEntity<ResponseSuccess> updatePet(@PathVariable(value = "id") Long id, @RequestBody Pet pet) { // "http://localhost:8080/api/v1/pets/{id}"
//        Pet updatedPet = petService.updatePet(id, pet);
//        log.info("The updated pet is: {}", updatedPet);
//        return ResponseEntity.ok(updatedPet);
//    }
//
//    @DeleteMapping(path = {"/{id}"})
//    public ResponseEntity<ResponseSuccess> deletePet(@PathVariable(value = "id") Long id) { // "http://localhost:8080/api/v1/pets/{id}"
//        petService.deletePet(id);
//        log.info("The pet with ID {}, is deleted", id);
//        return ResponseEntity.ok("The pet with ID " + id + ", is deleted");
//    }
//
//    /**
//     * TODO: Implement this method
//     */
//    @PostMapping(path = {"/{id}"},
//            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public ResponseEntity<Pet> updateWithForm(@PathVariable int id,
//                                              @RequestParam MultiValueMap<String, String> paramMap) throws Exception {
//
//        /*if (id < 1) { //|| !UsefulUtils.isInteger(String.valueOf(id))
//            //log.info("400 - Invalid ID supplied");
//            throw new IllegalArgumentException("400 - Invalid ID supplied");
//        }*/
//
////        Pet pet = new Pet(petModel);
////        pet.setPhotoUrls(pet.getPhotoUrls());
////        pet.setTags(pet.getTags());
////        Pet petPersisted = petRepository.save(pet);
//
////        log.info("The new pet:\n" + petPersisted + "\nis added, with the id: " + petPersisted.getId());
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
}
