package com.thomasmylonas.petstore_api_web_app._;

import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.InvalidInputSuppliedException;
import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.ResourceNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;

public interface EntityController<T, TModel> {

    /**
     * It returns the Entity, with the given in the parameter id
     *
     * @param id The @PathVariable of the request
     * @return The ResponseEntity of the request
     * @throws InvalidInputSuppliedException If the input is invalid (eg. negative id)
     * @throws ResourceNotFoundException     If the resource is not found
     */
    //ResponseEntity<TModel> getById(Long id);

    /**
     * It returns the list of all Entities
     *
     * @return The list of all Entities
     */
    //List<T> getAll();

    /**
     * It saves a new Entity and its dependent Entities, and the Exceptions thrown are handled
     * in the "ExceptionsHandlerController"
     *
     * @param entityModel The @RequestBody of the request
     * @param ucb         The UriComponentsBuilder
     * @return The ResponseEntity of the request
     * @throws HttpMessageNotReadableException    If RequestBody is not like "TModel", but like other models
     * @throws HttpMediaTypeNotSupportedException Thrown runtime, if RequestBody is null or not valid JSON
     */
    //ResponseEntity<T> save(TModel entityModel, UriComponentsBuilder ucb) throws HttpMessageNotReadableException, HttpMediaTypeNotSupportedException;

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
    //ResponseEntity<T> update(TModel entityModel);

    /**
     * It deletes the Entity, with the given in the parameter id
     *
     * @param id The @PathVariable of the request
     * @return The ResponseEntity<SuccessStatus>
     * @throws InvalidInputSuppliedException If the input is invalid (eg. negative id)
     * @throws ResourceNotFoundException     If the resource to be deleted is not found
     */
    //ResponseEntity<SuccessStatus> delete(Long id);
}
