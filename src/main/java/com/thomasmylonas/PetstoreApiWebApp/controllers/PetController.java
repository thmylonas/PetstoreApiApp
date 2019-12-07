package com.thomasmylonas.PetstoreApiWebApp.controllers;

import com.thomasmylonas.PetstoreApiWebApp.models.Pet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class PetController implements GeneralController<Pet> {
    @Override
    @RequestMapping(path = "pet",
            method = RequestMethod.GET,
            consumes = "application/json")
    public Pet getById(long id) {
        return null;
    }

    @Override
    public Pet postById(long id, Pet pet) {
        return null;
    }

    @Override
    public Pet deleteById(long id) {
        return null;
    }

    @Override
    public Pet post(Pet pet) {
        return null;
    }

    @Override
    public Pet put(Pet pet) {
        return null;
    }
}
