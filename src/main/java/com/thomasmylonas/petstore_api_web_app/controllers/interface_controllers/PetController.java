package com.thomasmylonas.petstore_api_web_app.controllers.interface_controllers;

import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.InvalidInputSuppliedException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PetController<T, TModel> extends EntityController<T, TModel> {

    /**
     * http://localhost:8080/PetstoreApiWebApp_war_exploded/pet/findByStatus?status=sold
     * It returns the list of Entities (Pets) filtered by status
     *
     * @param status status: available, pending, sold, and all the combinations
     * @return The petList filtered by status
     * @throws InvalidInputSuppliedException If the input is invalid (eg. status is not as defined)
     */
    ResponseEntity<List<TModel>> findByStatus(String status);
}
