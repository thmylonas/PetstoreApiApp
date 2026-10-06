package com.thomasmylonas.petstore_api_app.api.controllers;

import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetRequestDto;
import com.thomasmylonas.petstore_api_app.api.dtos.pet_dtos.PetResponseDto;
import com.thomasmylonas.petstore_api_app.api.models.ResponseBuilder;
import com.thomasmylonas.petstore_api_app.api.models.ResponseSuccess;
import com.thomasmylonas.petstore_api_app.api.services.PetService;
import com.thomasmylonas.petstore_api_app.api.validation.ValidatePetStatusType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = {"/api/v1/pets"})
@RequiredArgsConstructor
@Validated
public class PetController {

    private static final String REQUEST_MAPPING = "/api/v1/pets";

    private final PetService petService;
    private final ResponseBuilder responseBuilder;

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/pets/{id}"
     *
     * @param petId The "petId"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findPetById(@PathVariable(value = "id")
                                                       @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                                       Long petId) {
        final String message = "Success: The Pet with ID " + petId + " is found!";
        PetResponseDto petResponseDto = petService.findPetById(petId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("pet_response", petResponseDto));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/pets/by-name/{name}"
     *
     * @param petName The "petName"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(path = {"/by-name/{name}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findPetsByName(@PathVariable(value = "name")
                                                          @NotBlank(message = "The 'name' must not be null and must contain at least one non-whitespace character")
                                                          @Size(min = 3, max = 15, message = "The 'name' size must be between 3 and 15 characters (included)")
                                                          String petName) {
        final String message = "Success: The Pets with name " + petName + " are found!";
        List<PetResponseDto> petResponseDtos = petService.findPetsByName(petName);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("pets_by_name_response", petResponseDtos));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/pets?status=sold"
     *
     * @param petStatus status: "available", "pending", "sold", and all the combinations
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(params = {"status"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findPetsByStatus(@RequestParam(value = "status", defaultValue = "available") @ValidatePetStatusType String petStatus) {
        final String message = "Success: The Pets with status are found!";
        List<PetResponseDto> petByStatusResponseDtos = petService.findPetsByStatus(petStatus);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("pets_by_status_response", petByStatusResponseDtos));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/pets"
     *
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllPets() {
        final String message = "Success: The Pets are found!";
        List<PetResponseDto> petResponseDtos = petService.findAllPets();
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("pets_response", petResponseDtos));
    }

    /**
     * Endpoint:
     * - GET, "http://localhost:8080/api/v1/pets?sort=field&dir=direction"
     *
     * @param sortBy        The "sortBy"
     * @param sortDirection The "sortDirection"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @GetMapping(params = {"sort", "dir"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> findAllPetsSorted(@RequestParam(value = "sort", defaultValue = "id", required = false) String sortBy,
                                                             @RequestParam(value = "dir", defaultValue = "asc", required = false) String sortDirection) {
        final String message = "Success: The Pets are found!";
        List<PetResponseDto> petResponseDtos = petService.findAllPetsSorted(sortBy, sortDirection);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("pets_response", petResponseDtos));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/pets"
     *
     * @param petRequestDto The "petRequestDto"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> savePet(@RequestBody @Valid PetRequestDto petRequestDto) {

        final String message = "Created: The Pet has been created successfully!";
        PetResponseDto savedPetResponseDto = petService.savePet(petRequestDto);
        String savedPetUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // "http://localhost:8080"
                .path(REQUEST_MAPPING + "/{id}")
                .buildAndExpand(savedPetResponseDto.id())
                .toUriString();
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, savedPetUri, Map.of("saved_pet_response", savedPetResponseDto));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/pets/all"
     *
     * @param petRequestDtos The "petRequestDtos"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PostMapping(path = {"/all"})
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseSuccess> saveAllPets(@RequestBody
                                                       @NotEmpty(message = "The 'petRequestDtos' must not be null or empty")
                                                       List<@Valid PetRequestDto> petRequestDtos) {
        final String message = "Created: The Pets have been created successfully!";
        List<PetResponseDto> savedPetResponseDtos = petService.saveAllPets(petRequestDtos);
        return responseBuilder.buildResponseSuccess(HttpStatus.CREATED, message, Map.of("saved_pets_response", savedPetResponseDtos));
    }

    /**
     * Endpoint:
     * - PUT, "http://localhost:8080/api/v1/pets/{id}"
     *
     * @param petRequestDto The "petRequestDto"
     * @param petId         The "petId"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PutMapping(path = {"/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> updatePet(@RequestBody @Valid PetRequestDto petRequestDto,
                                                     @PathVariable(value = "id")
                                                     @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                                     Long petId) {
        final String message = "Success: The Pet with ID " + petId + " has been updated successfully!";
        PetResponseDto updatedPetResponseDto = petService.updatePet(petRequestDto, petId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("updated_pet_response", updatedPetResponseDto));
    }

    /**
     * Endpoint:
     * - DELETE, "http://localhost:8080/api/v1/pets/{id}"
     *
     * @param petId The "petId"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @DeleteMapping(path = {"/{id}"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> deletePetById(@PathVariable(value = "id")
                                                         @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                                         Long petId) {
        final String message = "Success: The Pet with ID " + petId + " has been deleted successfully!";
        petService.deletePetById(petId);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("message", message));
    }

    /**
     * Endpoint:
     * - POST, "http://localhost:8080/api/v1/pets/update-pet-with-form"
     *
     * @param petId  The "petId"
     * @param name   The "name"
     * @param status The "status"
     * @return The ResponseEntity<ResponseSuccess>
     */
    @PostMapping(path = {"/update-pet-with-form"}) //consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseSuccess> updatePetWithForm(@ModelAttribute(value = "id")
                                                             @PositiveOrZero(message = "The 'id' must be a positive number or 0")
                                                             Long petId,
                                                             @ModelAttribute(value = "name")
                                                             @NotBlank(message = "The 'name' must not be null and must contain at least one non-whitespace character")
                                                             @Size(min = 3, max = 15, message = "The 'name' size must be between 3 and 15 characters (included)")
                                                             String name,
                                                             @ModelAttribute(value = "status") @ValidatePetStatusType String status
                                                             //, @PathVariable(value = "id") Long id
    ) {
        final String message = String.format("id: '%d', name: '%s', status: '%s'", petId, name, status);
        PetResponseDto updatedPetResponseDto = petService.updatePetWithForm(petId, name, status);
        return responseBuilder.buildResponseSuccess(HttpStatus.OK, message, Map.of("updated_pet_response", updatedPetResponseDto));
    }
}
