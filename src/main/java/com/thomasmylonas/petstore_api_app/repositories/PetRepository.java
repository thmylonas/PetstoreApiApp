package com.thomasmylonas.petstore_api_app.repositories;

import com.thomasmylonas.petstore_api_app.entities.Pet;
import com.thomasmylonas.petstore_api_app.enums.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<List<Pet>> findByName(String name);

    Optional<List<Pet>> findByStatus(PetStatus status);
}
