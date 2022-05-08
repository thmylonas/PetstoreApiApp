package com.thomasmylonas.petstore_api_web_app.service_layer.services;

import com.thomasmylonas.petstore_api_web_app.data_access_layer.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.PetModel;
import com.thomasmylonas.petstore_api_web_app.service_layer.models.response_status_models.SuccessStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Override
    public Pet getPetById(Long id) {
        return null;
    }

    @Override
    public List<Pet> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Pet> save(PetModel entityModel) throws HttpMessageNotReadableException, HttpMediaTypeNotSupportedException {
        return null;
    }

    @Override
    public ResponseEntity<Pet> update(PetModel entityModel) {
        return null;
    }

    @Override
    public ResponseEntity<SuccessStatus> delete(Long id) {
        return null;
    }
}
