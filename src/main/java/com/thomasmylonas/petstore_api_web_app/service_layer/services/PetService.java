package com.thomasmylonas.petstore_api_web_app.service_layer.services;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.ResourceNotFoundException;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.PetModel;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.response_status_models.SuccessStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import java.util.List;

public interface PetService {

    /**
     * The method fetches the Pet, by the given ID
     *
     * @param id The ID of the Pet to fetch
     * @return The Pet we need to fetch
     * @throws InvalidInputSuppliedException If the input is invalid (e.g. negative id)
     * @throws ResourceNotFoundException     If the resource is not found
     */
    Pet getPetById(Long id);

    /**
     * It returns the list of all Entities
     *
     * @return The list of all Entities
     */
    List<Pet> getAll();

    /**
     * It saves a new Entity and its dependent Entities, and the Exceptions thrown are handled
     * in the "ExceptionsHandlerController"
     *
     * @param entityModel The @RequestBody of the request
     * @return The ResponseEntity of the request
     * @throws HttpMessageNotReadableException    If RequestBody is not like "TModel", but like other models
     * @throws HttpMediaTypeNotSupportedException Thrown runtime, if RequestBody is null or not valid JSON
     */
    ResponseEntity<Pet> save(PetModel entityModel)
            throws HttpMessageNotReadableException, HttpMediaTypeNotSupportedException;

    /**
     * It updates an existing Entity and its dependent Entities, with the given in the parameter Entity,
     * with the same id, and the Exceptions thrown are handled in the "ExceptionsHandlerController"
     *
     * @param entityModel The @RequestBody of the request
     * @return The ResponseEntity of the request
     * @throws InvalidInputSuppliedException If the input is invalid (eg. negative id)
     * @throws ResourceNotFoundException     If the resource to be updated is not found
     * @throws Exception                     405: Validation exception // Not implemented
     */
    ResponseEntity<Pet> update(PetModel entityModel);

    /**
     * It deletes the Entity, with the given in the parameter id
     *
     * @param id The @PathVariable of the request
     * @return The ResponseEntity<SuccessStatus>
     * @throws InvalidInputSuppliedException If the input is invalid (eg. negative id)
     * @throws ResourceNotFoundException     If the resource to be deleted is not found
     */
    ResponseEntity<SuccessStatus> delete(Long id);
}
