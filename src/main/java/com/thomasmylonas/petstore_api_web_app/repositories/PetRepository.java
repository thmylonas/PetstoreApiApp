package com.thomasmylonas.petstore_api_web_app.repositories;

import com.thomasmylonas.petstore_api_web_app.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.models.enums.PetStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<List<Pet>> findByName(String name);

    Optional<List<Pet>> findByStatus(PetStatusEnum status);
}
