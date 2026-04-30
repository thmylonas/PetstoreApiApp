package com.thomasmylonas.petstore_api_app.controllers;

import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.models.ResponseSuccess;
import com.thomasmylonas.petstore_api_app.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/pets")
@RequiredArgsConstructor
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

    @GetMapping(path = {"/{name}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findPetsByName(@PathVariable(value = "name") String name) {
        final String message = "Success: The Pets with name " + name + " are found!";
        List<PetResponseDto> petResponseDtos = petService.findPetsByName(name);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("pets_by_name_response", petResponseDtos));
    }

    /**
     * "http://localhost:8080/api/v1/pets?status=sold"
     * It returns the list of Pets filtered by status
     *
     * @param status status: available, pending, sold, and all the combinations
     * @return The pets filtered by status
     */
    @GetMapping(params = {"status"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findPetsByStatus(@RequestParam(value = "status", defaultValue = "available") String status) {
        final String message = "Success: The Pets with status are found!";
        List<PetResponseDto> petByStatusResponseDtos = petService.findPetsByStatus(status);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("pets_by_status_response", petByStatusResponseDtos));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllPets() { // "http://localhost:8080/api/v1/pets"
        final String message = "Success: The Pets are found!";
        List<PetResponseDto> petResponseDtos = petService.findAllPets();
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("pets_response", petResponseDtos));
    }

    @GetMapping(params = {"sort", "dir"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllPetsSorted(@RequestParam(value = "sort", defaultValue = "id", required = false) String sortBy,
                                                             @RequestParam(value = "dir", defaultValue = "asc", required = false) String sortDirection) { // "http://localhost:8080/api/v1/pets?sort=field&dir=direction"
        final String message = "Success: The Pets are found!";
        List<PetResponseDto> petResponseDtos = petService.findAllPetsSorted(sortBy, sortDirection);
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

    @PutMapping(path = {"/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> updatePet(@PathVariable(value = "id") Long id, @RequestBody PetRequestDto petRequestDto) { // "http://localhost:8080/api/v1/pets/{id}"
        final String message = "Success: The Pet with ID " + id + " has been updated successfully!";
        PetResponseDto updatedPetResponseDto = petService.updatePet(id, petRequestDto);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("updated_pet_response", updatedPetResponseDto));
    }

    @DeleteMapping(path = {"/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> deletePetById(@PathVariable(value = "id") Long id) { // "http://localhost:8080/api/v1/pets/{id}"
        final String message = "Success: The Pet with ID " + id + " has been deleted successfully!";
        petService.deletePetById(id);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("message", message));
    }

    @PostMapping(
            path = {"/update-pet/{id}"}
//            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    public ResponseEntity<ResponseSuccess> updatePetWithForm(@ModelAttribute(value = "name") String name,
                                                             @ModelAttribute(value = "status") String status,
                                                             @ModelAttribute(value = "category") String category,
                                                             @PathVariable(value = "id") Long id) {
        final String message = String.format("name: '%s', status: '%s', category: '%s'", name, status, category);
        PetResponseDto updatedPetResponseDto = petService.updatePetWithForm(id, name, status, category);
        return responseBuilder.buildResponse(HttpStatus.OK, message, Map.of("updated_pet_response", updatedPetResponseDto));
    }
}
