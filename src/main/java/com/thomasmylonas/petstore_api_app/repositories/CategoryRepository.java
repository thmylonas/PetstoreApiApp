package com.thomasmylonas.petstore_api_app.repositories;

import com.thomasmylonas.petstore_api_app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<List<Category>> findByName(String name);
}
