package com.thomasmylonas.petstore_api_app.api.repositories;

import com.thomasmylonas.petstore_api_app.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<List<Category>> findByName(String name);
}
