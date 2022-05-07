package com.thomasmylonas.petstore_api_web_app.data_access.repositories;

import com.thomasmylonas.petstore_api_web_app.data_access.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
