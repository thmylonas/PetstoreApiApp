package com.thomasmylonas.petstore_api_web_app._;

import com.thomasmylonas.petstore_api_web_app.service_layer.exceptions.InvalidInputSuppliedException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface _temp_PetController<T, TModel> extends EntityController<T, TModel> {

    /**
     * http://localhost:8080/pet/findByStatus?status=sold
     * It returns the list of Entities (Pets) filtered by status
     *
     * @param status status: available, pending, sold, and all the combinations
     * @return The petList filtered by status
     * @throws InvalidInputSuppliedException If the input is invalid (eg. status is not as defined)
     */
    ResponseEntity<List<TModel>> findByStatus(String status);
}
